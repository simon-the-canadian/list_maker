package com.sbproduction.listmaker.tools;

import com.bluelinelabs.logansquare.typeconverters.TypeConverter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by simon on 1/2/16.
 */
public class ACLConverter implements TypeConverter<String>
{
	/*
	"ACL": {
        "*": {
          "read": true,
          "write": true
        },
        "11vqTVFaKN": {
          "read": true,
          "write": true
        },
        "vlN8amYWop": {
          "read": true,
          "write": true
        }
      }
	 */

	@Override
	public String parse(JsonParser jsonParser) throws IOException
	{
		//TODO: this needs some serious work to match to response
		JSONObject jsonObject = new JSONObject();

		try
		{
			String test = jsonParser.getValueAsString(null);
			while (jsonParser.nextToken() != JsonToken.END_OBJECT)
			{
				//name of acl object (id or *)
				String fieldName = jsonParser.getCurrentName();

				if (jsonParser.nextToken() == JsonToken.START_OBJECT)
				{
					JSONObject aclObject = new JSONObject();

					while (jsonParser.nextToken() != JsonToken.END_OBJECT)
					{
						//read
						String aclPropertyName = jsonParser.getCurrentName();
						test = jsonParser.getValueAsString(null);
						jsonParser.nextToken();
						test = jsonParser.getValueAsString(null);

						//go to the next field which is the value
						aclObject.put(aclPropertyName, jsonParser.getValueAsString(null));
						test = jsonParser.getValueAsString(null);
					}

					jsonObject.put(fieldName, aclObject);
				}



//				jsonParser.nextToken();
//				//read
//				jsonParser.nextToken();
//				String aclPropertyName = jsonParser.getCurrentName();
//
//				//go to the next field which is the value
//				jsonParser.nextToken();
//				aclObject.put(aclPropertyName, jsonParser.getValueAsString());
//
//				//write
//				jsonParser.nextToken();
//				aclPropertyName = jsonParser.getCurrentName();
//
//				//go to the next field which is the value
//				jsonParser.nextToken();
//				aclObject.put(aclPropertyName, jsonParser.getValueAsString());

//				jsonParser.nextToken(); //move on past the end object marker
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	@Override
	public void serialize(String object, String fieldName, boolean writeFieldNameForObject, JsonGenerator jsonGenerator) throws IOException
	{
		if (object != null)
		{
			jsonGenerator.writeFieldName(fieldName);
			jsonGenerator.writeRaw(":" + object);
		}
		else
		{
			jsonGenerator.writeFieldName(fieldName);
			jsonGenerator.writeNull();
		}
	}
}
