/**
 *
 */
package com.sitv.skyshop.common.utils.converters;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author zfj20
 */
public class CurrencyFormat extends JsonSerializer<BigDecimal> {

	private DecimalFormat format = new DecimalFormat("0.00");

	public void serialize(BigDecimal d, JsonGenerator g, SerializerProvider p) throws IOException, JsonProcessingException {
		if (d != null && d.compareTo(BigDecimal.ZERO) > 0) {
			// g.writeNumber(NumberFormat.getCurrencyInstance().format(d));
			g.writeNumber(format.format(d));
		}
	}

}
