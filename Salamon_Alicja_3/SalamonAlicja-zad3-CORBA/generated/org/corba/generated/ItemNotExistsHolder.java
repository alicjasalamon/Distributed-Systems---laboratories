package org.corba.generated;

/**
* org/corba/generated/ItemNotExistsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from MiddlewareTestbed.idl
* Friday, April 12, 2013 1:59:25 PM CEST
*/

public final class ItemNotExistsHolder implements org.omg.CORBA.portable.Streamable
{
  public org.corba.generated.ItemNotExists value = null;

  public ItemNotExistsHolder ()
  {
  }

  public ItemNotExistsHolder (org.corba.generated.ItemNotExists initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.corba.generated.ItemNotExistsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.corba.generated.ItemNotExistsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.corba.generated.ItemNotExistsHelper.type ();
  }

}
