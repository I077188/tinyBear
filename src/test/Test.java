package test;

import java.io.IOException;

import com.dba.util.LOG;
import com.dba.util.Result;

public class Test {

	public static void main(String[] args) throws IOException {

		LOG log = new LOG();
		Result result = new Result();
		
		LOG.info("test");
		Result.info("test");
		
		LOG.close();
		Result.close();
	}
}
