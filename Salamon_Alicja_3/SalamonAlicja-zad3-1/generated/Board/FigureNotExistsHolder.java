package Board;

/**
* Board/FigureNotExistsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Board.idl
* Wednesday, April 17, 2013 3:31:24 PM CEST
*/

public final class FigureNotExistsHolder implements org.omg.CORBA.portable.Streamable
{
  public Board.FigureNotExists value = null;

  public FigureNotExistsHolder ()
  {
  }

  public FigureNotExistsHolder (Board.FigureNotExists initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Board.FigureNotExistsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Board.FigureNotExistsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Board.FigureNotExistsHelper.type ();
  }

}
