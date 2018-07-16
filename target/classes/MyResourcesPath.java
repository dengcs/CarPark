package main.resources;

public final class MyResourcesPath {
	public static String getProtocalPath()
	{
		String myPath = MyResourcesPath.class.getResource("").getPath();
		
		myPath = myPath.concat("protocal.json");
		
		return myPath;
	}
}
