package Board;


/**
* Board/FigureWrongParameters.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Board.idl
* Wednesday, April 17, 2013 3:31:24 PM CEST
*/

public final class FigureWrongParameters extends org.omg.CORBA.UserException
{

  public FigureWrongParameters ()
  {
    super(FigureWrongParametersHelper.id());
  } // ctor


  public FigureWrongParameters (String $reason)
  {
    super(FigureWrongParametersHelper.id() + "  " + $reason);
  } // ctor

} // class FigureWrongParameters
