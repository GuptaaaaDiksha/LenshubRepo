package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Map;
import java.util.Set;

import javax.xml.transform.stream.StreamSource;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import utils.listeners.TestListener;

public class SoftAssert extends Assertion {

    private final Map<AssertionError, IAssert> m_errors;
    private final Map<Exception, IAssert> m_errors2;
    private final Map<String, IAssert> m_pass;

    String pmsg = "";
    String fmsg = "";

    public SoftAssert() {
        this.m_errors = Maps.newLinkedHashMap();
        this.m_pass = Maps.newLinkedHashMap();
        this.m_errors2 = Maps.newLinkedHashMap();
    }

    private List<String> messages = new ArrayList<>();

    public List<String> getMessages() {
        return messages;
    }
  ArrayList<String> AllPTCIds = new ArrayList<>(); // create list with duplicates...
    ArrayList<String> AllFTCIds = new ArrayList<>(); // create list with duplicates...
    int pcount = 0;
    int fcount  =0;

    @Override
    public void onAssertSuccess(IAssert<?> assertCommand) {
        pmsg = assertCommand.getMessage();
        super.onAssertSuccess(assertCommand);
        messages.add(assertCommand.getMessage());
        for (String message : messages) {
            System.out.println(message + " ====<PASSED ASSERT MESSAGE>");
        }
        String pids = pmsg.split(":")[0];

        if (pids.contains(",")) {
            String[] t1 = pids.split(",");
            // count = count + t1.length;
            for (String test : t1) {
                AllPTCIds.add(test);
            }
        } 
         else{
            AllPTCIds.add(pids);
        }
        

        Set<String> uniqueTCIds = new HashSet<String>(AllPTCIds);
        System.out.println("UNIQUE Passed count: " + uniqueTCIds.size());

        pcount=uniqueTCIds.size();
        TestListener.passIds(uniqueTCIds);
    }

    protected void doAssert(IAssert a) {
        onBeforeAssert(a);
        try {
            a.doAssert();
            onAssertSuccess(a);

        } catch (AssertionError ex) {
            onAssertFailure(a, ex);
            this.m_errors.put(ex, a);
            fmsg = a.getMessage();
        String fids = fmsg.split(":")[0];

        if (fids.contains(",")) {
            String[] t1 = fids.split(",");
            for (String test : t1) {
                AllFTCIds.add(test);
            }
        } 
         else{
            AllFTCIds.add(fids);
        }
        

        Set<String> uniqueTCIds = new HashSet<String>(AllFTCIds);
        System.out.println("UNIQUE Failed count: " + uniqueTCIds.size());
        fcount = uniqueTCIds.size();
        TestListener.failIds(uniqueTCIds);
       
        }
        finally {
            this.m_pass.put(pmsg, a);
            onAfterAssert(a);
        }
    }

    public void assertAll() {
    TestListener.passcount(pcount);
        if (!(this.m_errors.isEmpty())) {
            StringBuilder sb = new StringBuilder("Total assertion failed: " + m_errors.size());
            sb.append("\n\t");
            sb.append("The following asserts failed:");
               TestListener.failcount(fcount);
            throw new AssertionError(sb.toString());
        }
        

    }
}
