package com.example.tutorial;

import com.example.tutorial.AddressBookProtos.Person;
import com.google.protobuf.InvalidProtocolBufferException;

public class TestProtoBuf {

	public static void main(String[] args) {
		// 生成对象
		Person john = Person.newBuilder().setId(1234).setName("John Doe").setEmail("jdoe@example.com")
				.addPhone(Person.PhoneNumber.newBuilder().setNumber("555-4321").setType(Person.PhoneType.HOME)).build();

		System.out.println(john.toString());

		// 序列化为二进制数组
		byte[] johnbyte = john.toByteArray();

		// 反序列化(中间过程你可以认为是经过了网络传输，文件存储等)
		// 注意对比两个System.out的输出
		try {
			Person john2 = Person.parseFrom(johnbyte);
			System.out.println(john2.toString());
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
