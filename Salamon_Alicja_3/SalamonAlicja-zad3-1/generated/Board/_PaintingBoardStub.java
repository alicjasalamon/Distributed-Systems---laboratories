package Board;


/**
* Board/_PaintingBoardStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Board.idl
* Wednesday, April 17, 2013 3:31:24 PM CEST
*/

public class _PaintingBoardStub extends org.omg.CORBA.portable.ObjectImpl implements Board.PaintingBoard
{

  public Board.Figure createFigure (String type, Board.Parameters params) throws Board.FigureNameNotExists, Board.FigureWrongParameters
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createFigure", true);
                $out.write_string (type);
                Board.ParametersHelper.write ($out, params);
                $in = _invoke ($out);
                Board.Figure $result = Board.FigureHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:Board/FigureNameNotExists:1.0"))
                    throw Board.FigureNameNotExistsHelper.read ($in);
                else if (_id.equals ("IDL:Board/FigureWrongParameters:1.0"))
                    throw Board.FigureWrongParametersHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return createFigure (type, params        );
            } finally {
                _releaseReply ($in);
            }
  } // createFigure

  public Board.Figure removeFigure (Board.Figure figure) throws Board.FigureNotExists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("removeFigure", true);
                Board.FigureHelper.write ($out, figure);
                $in = _invoke ($out);
                Board.Figure $result = Board.FigureHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:Board/FigureNotExists:1.0"))
                    throw Board.FigureNotExistsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return removeFigure (figure        );
            } finally {
                _releaseReply ($in);
            }
  } // removeFigure

  public void register (String name, Board.Listener listener) throws Board.UserAlreadyExists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("register", true);
                $out.write_string (name);
                Board.ListenerHelper.write ($out, listener);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:Board/UserAlreadyExists:1.0"))
                    throw Board.UserAlreadyExistsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                register (name, listener        );
            } finally {
                _releaseReply ($in);
            }
  } // register

  public void unregister (String name) throws Board.UserNotExists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("unregister", true);
                $out.write_string (name);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:Board/UserNotExists:1.0"))
                    throw Board.UserNotExistsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                unregister (name        );
            } finally {
                _releaseReply ($in);
            }
  } // unregister

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Board/PaintingBoard:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _PaintingBoardStub
