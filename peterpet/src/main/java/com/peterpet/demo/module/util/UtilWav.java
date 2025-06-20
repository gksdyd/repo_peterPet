package com.peterpet.demo.module.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public class UtilWav {
	
	private static byte[] audioBytes = new byte[1024];
	private static byte[] buff = new byte[1024];
	private static int read;

	public static File multipartFileToFile(MultipartFile multipartFile) throws IOException {
	    File file = File.createTempFile("upload_", multipartFile.getOriginalFilename());
	    multipartFile.transferTo(file);
	    return file;
	}
	
	public static byte[] getWavData(File file) throws IOException, InterruptedException {
//		return changeFormat(AudioToByte(file));
		return AudioToByte(file);
	}
	
	public static byte[] changeFormat(byte[] audioFileContent)
			throws InterruptedException, IOException {
		Path input = Files.createTempFile("audio_input", ".bin");
	    Path output = Files.createTempFile("audio_output", ".wav");

	    Files.write(input, audioFileContent);

	    ProcessBuilder pb = new ProcessBuilder(
	        "ffmpeg", "-y", "-i", input.toString(),
	        "-acodec", "pcm_s16le", "-ar", "16000", "-ac", "1", output.toString()
	    );
	    pb.redirectErrorStream(true);
	    Process process = pb.start();
	    process.waitFor();

	    byte[] wavBytes = Files.readAllBytes(output);

	    Files.deleteIfExists(input);
	    Files.deleteIfExists(output);
	    return wavBytes;
	}
	
	public static byte[] AudioToByte(File file) throws IOException {
		FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedInputStream in = new BufferedInputStream(fstream);

		while ((read = in.read(buff)) > 0) {
			out.write(buff, 0, read);
		}
		out.flush();
		audioBytes = out.toByteArray();
		
		out.close();
		in.close();
		fstream.close();
		
		if (file.exists()) {
		    boolean deleted = file.delete();
		    if (!deleted) {
		        System.err.println("파일 삭제 실패: " + file.getAbsolutePath());
		    }
		}
		return audioBytes;
	}
}
