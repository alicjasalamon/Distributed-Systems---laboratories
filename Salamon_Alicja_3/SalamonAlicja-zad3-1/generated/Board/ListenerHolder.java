package Board;

/**
* Board/ListenerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Board.idl
* Wednesday, April 17, 2013 3:31:24 PM CEST
*/

public final class ListenerHolder implements org.omg.CORBA.portable.Streamable
{
  public Board.Listener value = null;

  public ListenerHolder ()
  {
  }

  public ListenerHolder (Board.Listener initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Board.ListenerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Board.ListenerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Board.ListenerHelper.type ();
  }

}
