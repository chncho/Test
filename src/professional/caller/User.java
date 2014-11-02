package professional.caller;

public class User {

	public void printuser(){
		System.out.println("uuuuuuuuuuuuuuuuu");
		System.out.println(getCaller());
		System.out.println("-----------------");
	}
	public static String getCaller()

	{
		StringBuffer sb = new StringBuffer();
		StackTraceElement stack[] = new Throwable().getStackTrace();
		for (int i = 0; i < stack.length; i++)
		{
			StackTraceElement ste = stack[i];
			
			sb.append(ste.getClassName() + "." + ste.getMethodName()
					+ "(...);");
			;
			sb.append(i + "--" + ste.getMethodName());
			sb.append(i + "--" + ste.getFileName());
			sb.append(i + "--" + ste.getLineNumber());
			sb.append("\n");
		}
		return sb.toString();
	}
}
