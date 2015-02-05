package GetValute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OutValutes {
	public String getCurrentDateForCourse()
	{
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 
		currentDate = new Date(); 		
		return sdf.format(currentDate);
	}

	public ArrayList getValuteList()
	{
		String api = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + getCurrentDateForCourse();//21.10.2014";
		//System.out.println("Отправляемая строка для запроса = " + api);
		ArrayList valuteList = new ArrayList();
	
		try 
		{
			URL url = new URL(api);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET"); 
		
			if(connection.getResponseCode() != 200)
			{
				throw new RuntimeException("ERROR!!!");
			}
	
			InputStreamReader inpsr = new InputStreamReader(connection.getInputStream());
		
			//BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			BufferedReader br = new BufferedReader(inpsr);
		
			String importValutes = "";
			int bri = 0;
			do
			{
				bri = br.read();
				importValutes = importValutes + (char)bri;
			}
			while(bri != -1);
		
			//System.out.println(importValutes);
	
			String subBuf = "";
			int iBegin = 0;
			int iEnd = 0;
			int iBuf = 0;
			while(iBegin != -1)
			{
				iBegin = importValutes.indexOf("<CharCode>", iBuf);
				iEnd = importValutes.indexOf("</CharCode>", iBegin);
				if(iBegin != -1)
				{
					subBuf = importValutes.substring(iBegin, iEnd + "</CharCode>".length()); 
				}
				iBegin = importValutes.indexOf("<Nominal>", iBuf);
				iEnd = importValutes.indexOf("</Nominal>", iBegin);
				if(iBegin != -1)
				{
					subBuf = subBuf + importValutes.substring(iBegin, iEnd + "</Nominal>".length());
					iBuf = iEnd;
				}
				iBegin = importValutes.indexOf("<Name>", iBuf);
				iEnd = importValutes.indexOf("</Name>", iBegin);
				if(iBegin != -1)
				{	
					subBuf = subBuf + importValutes.substring(iBegin, iEnd + "</Name>".length());
					iBuf = iEnd + 1;
				}
				iBegin = importValutes.indexOf("<Value>", iBuf);
				iEnd = importValutes.indexOf("</Value>", iBegin);
				if(iBegin != -1)
				{
					subBuf = subBuf + importValutes.substring(iBegin, iEnd + "</Value>".length());
					iBuf = iEnd + 1;
					valuteList.add(subBuf);
				}
			
			}
			//	System.out.println(valuteList);
		}
		
		catch (MalformedURLException e)
		{
			System.out.println("MalFormedExceptionN error!!!");
		}
		catch (IOException e) 
		{
			System.out.println("IOException error!!!");
		}
		return valuteList;
	}
	
	public ArrayList getValuteList(String dateddmmyyyy)
	{
		String api = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + dateddmmyyyy;//21.10.2014";
		ArrayList valuteList = new ArrayList();
		try 
		{
			URL url = new URL(api);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET"); 
			if(connection.getResponseCode() != 200)
			{
				throw new RuntimeException("ERROR!!!");
			}
			InputStreamReader inpsr = new InputStreamReader(connection.getInputStream());
			//BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			BufferedReader br = new BufferedReader(inpsr);
		
			String importValutes = "";
			int bri = 0;
			do
			{
				bri = br.read();
				importValutes = importValutes + (char)bri;
			}
			while(bri != -1);
			String subBuf = "";
			int iBegin = 0;
			int iEnd = 0;
			int iBuf = 0;
			while(iBegin != -1)
			{
				iBegin = importValutes.indexOf("<CharCode>", iBuf);
				iEnd = importValutes.indexOf("</CharCode>", iBegin);
				if(iBegin != -1)
				{
					subBuf = importValutes.substring(iBegin, iEnd + "</CharCode>".length()); 
				}
				iBegin = importValutes.indexOf("<Nominal>", iBuf);
				iEnd = importValutes.indexOf("</Nominal>", iBegin);
				if(iBegin != -1)
				{
					subBuf = subBuf + importValutes.substring(iBegin, iEnd + "</Nominal>".length());
					iBuf = iEnd;
				}
				iBegin = importValutes.indexOf("<Name>", iBuf);
				iEnd = importValutes.indexOf("</Name>", iBegin);
				if(iBegin != -1)
				{	
					subBuf = subBuf + importValutes.substring(iBegin, iEnd + "</Name>".length());
					iBuf = iEnd + 1;
				}
				iBegin = importValutes.indexOf("<Value>", iBuf);
				iEnd = importValutes.indexOf("</Value>", iBegin);
				if(iBegin != -1)
				{
					subBuf = subBuf + importValutes.substring(iBegin, iEnd + "</Value>".length());
					iBuf = iEnd + 1;
					valuteList.add(subBuf);
				}
			}
		}
		catch (MalformedURLException e)
		{
			System.out.println("MalFormedExceptionN error!!!");
		}
		catch (IOException e) 
		{
			System.out.println("IOException error!!!");
		}
		return valuteList;
	}
	public String getCharCodeValute(String currentValuteInfo)
	{
		String charCode = "";
		int iBegin = currentValuteInfo.indexOf("<CharCode>");
		int iEnd = currentValuteInfo.indexOf("</CharCode>");
		charCode = currentValuteInfo.substring(iBegin + "<CharCode>".length(), iEnd );
		return charCode;
	}
	public String getNominalValute(String currentValuteInfo)
	{
		String nominal = "";
		int iBegin = currentValuteInfo.indexOf("<Nominal>");
		int iEnd = currentValuteInfo.indexOf("</Nominal>");
		nominal = currentValuteInfo.substring(iBegin + "<Nominal>".length(), iEnd );
		return nominal;
	}
	public String getNameValute(String currentValuteInfo)
	{
		String name = "";
		int iBegin = currentValuteInfo.indexOf("<Name>");
		int iEnd = currentValuteInfo.indexOf("</Name>");
		name = currentValuteInfo.substring(iBegin + "<Name>".length(), iEnd );
		return name;
	}
	public String getValueValute(String currentValuteInfo)
	{
		String value = "";
		int iBegin = currentValuteInfo.indexOf("<Value>");
		int iEnd = currentValuteInfo.indexOf("</Value>");
		value = currentValuteInfo.substring(iBegin + "<Value>".length(), iEnd );
		return value;
	}
}
