package org.corba.generated;

/**
* org/corba/generated/AFactoryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from MiddlewareTestbed.idl
* Friday, April 12, 2013 1:59:25 PM CEST
*/

public final class AFactoryHolder implements org.omg.CORBA.portable.Streamable
{
  public org.corba.generated.AFactory value = null;

  public AFactoryHolder ()
  {
  }

  public AFactoryHolder (org.corba.generated.AFactory initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.corba.generated.AFactoryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.corba.generated.AFactoryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.corba.generated.AFactoryHelper.type ();
  }

}
