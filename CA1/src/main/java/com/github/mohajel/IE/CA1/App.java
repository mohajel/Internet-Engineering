package com.github.mohajel.IE.CA1;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Starting app" );

        MizdooniApp app = new MizdooniApp();
        CliHandler handler = new CliHandler(app);

        handler.start();
    }
}
