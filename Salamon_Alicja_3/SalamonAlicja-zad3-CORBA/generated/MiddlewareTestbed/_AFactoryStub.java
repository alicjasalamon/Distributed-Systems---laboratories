package MiddlewareTestbed;


/**
* MiddlewareTestbed/_AFactoryStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from MiddlewareTestbed.idl
* Friday, April 12, 2013 1:58:37 PM CEST
*/

public class _AFactoryStub extends org.omg.CORBA.portable.ObjectImpl implements MiddlewareTestbed.AFactory
{

  public MiddlewareTestbed.Item create_item (String name, String type) throws MiddlewareTestbed.ItemAlreadyExists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("create_item", true);
                $out.write_string (name);
                $out.write_string (type);
                $in = _invoke ($out);
                MiddlewareTestbed.Item $result = MiddlewareTestbed.ItemHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:MiddlewareTestbed/ItemAlreadyExists:1.0"))
                    throw MiddlewareTestbed.ItemAlreadyExistsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return create_item (name, type        );
            } finally {
                _releaseReply ($in);
            }
  } // create_item

  public MiddlewareTestbed.Item take_item (String name) throws MiddlewareTestbed.ItemNotExists, MiddlewareTestbed.ItemBusy
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("take_item", true);
                $out.write_string (name);
                $in = _invoke ($out);
                MiddlewareTestbed.Item $result = MiddlewareTestbed.ItemHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:MiddlewareTestbed/ItemNotExists:1.0"))
                    throw MiddlewareTestbed.ItemNotExistsHelper.read ($in);
                else if (_id.equals ("IDL:MiddlewareTestbed/ItemBusy:1.0"))
                    throw MiddlewareTestbed.ItemBusyHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return take_item (name        );
            } finally {
                _releaseReply ($in);
            }
  } // take_item

  public void release_item (String name) throws MiddlewareTestbed.ItemNotExists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("release_item", true);
                $out.write_string (name);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:MiddlewareTestbed/ItemNotExists:1.0"))
                    throw MiddlewareTestbed.ItemNotExistsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                release_item (name        );
            } finally {
                _releaseReply ($in);
            }
  } // release_item

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:MiddlewareTestbed/AFactory:1.0"};

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
} // class _AFactoryStub
