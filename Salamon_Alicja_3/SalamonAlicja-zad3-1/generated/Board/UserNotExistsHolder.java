package Board;

/**
* Board/UserNotExistsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Board.idl
* Wednesday, April 17, 2013 3:31:24 PM CEST
*/

public final class UserNotExistsHolder implements org.omg.CORBA.portable.Streamable
{
  public Board.UserNotExists value = null;

  public UserNotExistsHolder ()
  {
  }

  public UserNotExistsHolder (Board.UserNotExists initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Board.UserNotExistsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Board.UserNotExistsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Board.UserNotExistsHelper.type ();
  }

}
