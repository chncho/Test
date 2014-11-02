package professional.encryption.rsa;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import professional.fileAndStream.file.FileUtils;

public class RSACoderTestS {

	private static String publicKey;
	private static String privateKey;
	public static void main(String[] args) throws Exception {
//		Map<String, Object> keyMap = RSACoder.initKey();
//
//		publicKey = RSACoder.getPublicKey(keyMap);
//		privateKey = RSACoder.getPrivateKey(keyMap);
//		System.err.println("公钥: \n\r" + publicKey);
//		System.err.println("私钥： \n\r" + privateKey);

		publicKey = FileUtils.readFile("src/professional/encryption/rsa/public.key");
		privateKey = FileUtils.readFile("src/professional/encryption/rsa/private.key");
		System.out.println(publicKey);
		System.out.println(privateKey);
		
		
		{
		System.err.println("公钥加密——私钥解密");
		String inputStr = "[{\"aaa\":\"a__\",\"bbb\":\"b__\"},{\"aaa\":\"a__\",\"bbb\":\"b__\"},{\"aaa\":\"a__\",\"bbb\":\"b__\"},{\"aaa\":\"a__\",\"bbb\":\"b__\"},{\"aaa\":\"a__\",\"bbb\":\"b__\"},{\"aaa\":\"a__\",\"bbb\":\"b__\"}]";
		byte[] data = inputStr.getBytes();
		//加密数据
		byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);
		//解密数据
		byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
				privateKey);
		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n加密后: "+encodedData.toString()+"\n" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);
		}
		System.out.println("==================================================");
		{
		System.err.println("私钥加密——公钥解密");
		String inputStr = "sign";
		byte[] data = inputStr.getBytes();
		//加密数据
		byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);
		//解密数据
		byte[] decodedData = RSACoder
				.decryptByPublicKey(encodedData, publicKey);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n加密后: "+encodedData.toString()+"\n" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

		/*
		System.err.println("私钥签名——公钥验证签名");
		// 产生签名
		String sign = RSACoder.sign(encodedData, privateKey);
		System.err.println("签名:\r" + sign);

		// 验证签名
		boolean status = RSACoder.verify(encodedData, publicKey, sign);
		System.err.println("状态:\r" + status);
		assertTrue(status);
		*/
		}
	}
}
