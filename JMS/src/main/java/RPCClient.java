import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RPCClient {

	private Connection connection;
	private Channel channel;
	private String requestQueueName = "rpc_queue";
	private String replyQueueName;

	public RPCClient() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		this.connection = factory.newConnection();
		this.channel = this.connection.createChannel();

		this.replyQueueName = this.channel.queueDeclare().getQueue();
	}

	public String call(String message) throws IOException, InterruptedException {
		final String corrId = UUID.randomUUID().toString();

		AMQP.BasicProperties props = new AMQP.BasicProperties
				.Builder()
				.correlationId(corrId)
				.replyTo(this.replyQueueName)
				.build();

		this.channel.basicPublish("", this.requestQueueName, props, message.getBytes("UTF-8"));

		final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

		this.channel.basicConsume(this.replyQueueName, true, new DefaultConsumer(this.channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				if (properties.getCorrelationId().equals(corrId)) {
					response.offer(new String(body, "UTF-8"));
				}
			}
		});

		return response.take();
	}

	public void close() throws IOException {
		this.connection.close();
	}

	public static void main(String[] argv) {
		RPCClient fibonacciRpc = null;
		String response = null;
		try {
			fibonacciRpc = new RPCClient();

			System.out.println(" [x] Requesting fib(30)");
			response = fibonacciRpc.call("30");
			System.out.println(" [.] Got '" + response + "'");
		}
		catch  (IOException | TimeoutException | InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			if (fibonacciRpc!= null) {
				try {
					fibonacciRpc.close();
				}
				catch (IOException _ignore) {}
			}
		}
	}
}

