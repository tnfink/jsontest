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

    @Test
    public void testDiamondGraph() throws IOException {
        Node t1 = new Node("t1");
        Node t2 = new Node("t2");
        Node t3 = new Node("t3");
        Node t4 = new Node("t4");

        // build diamond
        t1.addLinkTo(t2);
        t1.addLinkTo(t3);
        t2.addLinkTo(t4);
        t3.addLinkTo(t4);

        serializationRoundtrip(t1);
    }


    private void serializationRoundtrip(final Object object) throws IOException {
        StringWriter stringWriter = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(stringWriter, object);
        stringWriter.flush();
        Assert.assertNotNull(stringWriter.getBuffer().toString());
        System.out.println("stringWriter = " + stringWriter.getBuffer());
    }


}
