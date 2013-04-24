package com.ystreibel.ibricol;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class AppelService {

	private static final String NAMESPACE = "http://10.0.2.2:8080";
	private static final String URL = "http://10.0.2.2:8080/Meteoloc/services/Meteoloc?wsdl";
	private static final String SOAP_ACTION = "call";
	private static final String METHOD_NAME = "call";

	public String call(String activity, String localisation, String date,
			String partOfTheDay) {

		String reponse = "";

		try {
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			request.addProperty("activity", activity);
			request.addProperty("localisation", localisation);
			request.addProperty("date", date);
			request.addProperty("partOfTheDay", partOfTheDay);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION, envelope);
			reponse = (String) envelope.getResponse();
		} catch (Exception e) {
			Log.e("call", "", e);
		}

		return reponse;
	}
}