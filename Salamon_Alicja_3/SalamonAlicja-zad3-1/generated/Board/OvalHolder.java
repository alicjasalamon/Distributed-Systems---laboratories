package Board;

/**
* Board/OvalHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Board.idl
* Wednesday, April 17, 2013 3:31:24 PM CEST
*/

public final class OvalHolder implements org.omg.CORBA.portable.Streamable
{
  public Board.Oval value = null;

  public OvalHolder ()
  {
  }

  public OvalHolder (Board.Oval initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Board.OvalHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Board.OvalHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Board.OvalHelper.type ();
  }

}