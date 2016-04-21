package ch.lren.hbpmip.rapidminer.tests.models;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections15.map.LinkedMap;

import com.fasterxml.jackson.core.JsonGenerator;

import com.rapidminer.operator.learner.lazy.DefaultLearner;
import com.rapidminer.operator.learner.lazy.DefaultModel;

import ch.lren.hbpmip.rapidminer.models.RapidMinerModel;


/**
 *
 * Trivial RapidMiner model 'DefautModel'
 *
 * @author Arnaud Jutzeler
 *
 */
public class RPMDefault extends RapidMinerModel<DefaultModel> {

    private String method;

    public RPMDefault() {
        this(System.getProperty("PARAM_MODEL_method", System.getenv("PARAM_MODEL_method")));
    }

    public RPMDefault(String method) {
        super(DefaultLearner.class);
        this.method = method;
    }

    @Override
    public Map<String, String> getParameters() {
        LinkedMap map = new LinkedMap<String, String>();
        map.put("method", this.method);
        return map;
    }

    @Override
    public void writeModelRepresentation(JsonGenerator jgen) throws IOException {

        jgen.writeObjectFieldStart("model");

        jgen.writeObjectFieldStart("type");
        jgen.writeStringField("doc", "The model parameter");
        jgen.writeStringField("name", "value");
        jgen.writeStringField("type", "double");
        jgen.writeEndObject();

        jgen.writeObjectFieldStart("init");
        jgen.writeNumberField("value", trainedModel.getValue());
        jgen.writeEndObject();

        jgen.writeEndObject();
    }


    @Override
    public void writeAction(JsonGenerator jgen) throws IOException {
        jgen.writeArrayFieldStart("action");
        jgen.writeStartObject();
        jgen.writeStringField("cell", "model");
        jgen.writeEndObject();
        jgen.writeEndArray();
    }
}