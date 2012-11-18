package de.akquinet.jsontest;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

public class JSonRoundtripTest {

    @Test
    public void testSimple() throws IOException {
        Employee bob = new Employee("bob", null);
        serializationRoundtrip(bob);
    }

    @Test
    public void testSimpleGraph() throws IOException {
        Employee bob = new Employee("bob", null);
        Employee alice = new Employee("alice", bob);
        serializationRoundtrip(alice);
    }

    @Test(expected = JsonMappingException.class)
    public void testCycleGraphWillFail() throws IOException {
        Employee bob = new Employee("bob", null);
        bob.setSuperior(bob); // top-chiefs manages themself
        Employee alice = new Employee("alice", bob);
        serializationRoundtrip(alice);
    }

    private void serializationRoundtrip(final Employee bob) throws IOException {
        StringWriter stringWriter = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(stringWriter, bob);
        stringWriter.flush();
        Assert.assertNotNull(stringWriter.getBuffer().toString());
        // System.out.println("stringWriter = " + stringWriter.getBuffer());
    }
}
