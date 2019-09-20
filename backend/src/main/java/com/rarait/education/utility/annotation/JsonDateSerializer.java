package com.rarait.education.utility.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Used to serialize Java.util.Date, which is not a common JSON
 * type, so we have to create a custom serialize method;.
 *
 * @author Loiane Groner
 *         http://loianegroner.com (English)
 *         http://loiane.com (Portuguese)
 *  @since 1.0
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:MM");

    @Override
    public void serialize(final Date date, final JsonGenerator generator, final SerializerProvider provider)
            throws IOException, JsonProcessingException {
        final String formattedDate = DATE_FORMAT.format(date);
        generator.writeString(formattedDate);
    }
}
