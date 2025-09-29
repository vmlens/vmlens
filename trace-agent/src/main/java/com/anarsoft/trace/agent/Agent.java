package com.anarsoft.trace.agent;

import com.vmlens.agent.AgentRuntime;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

/*
 *
 * only contains one class the agent.
 * Everything else is loaded bye an instanceof URLClassLoader with parent null.
 * The callback class is contained in the agent.callback package.
 *
 * trace.agent (agent, system classloader) -> callback /bootstrap classloader)
 *                                         -> runtime (Url Classloader)
 *
 *
 * We still need a flag for not tracing classes loaded from the bootstrap classloader.
 *
 *
 * @author Thomas.Krieger
 *
 */

public class /**/Agent {


    private static boolean alreadyStarted = false;

    public static boolean isAlreadyStarted() {
        return alreadyStarted;
    }

    public static void setAlreadyStarted(boolean alreadyStarted) {
        Agent.alreadyStarted = alreadyStarted;
    }


    public static void premain(String args, Instrumentation inst) {
        execute(args, inst);
    }


    private static void execute(String args, Instrumentation inst) {

        //	CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;

        synchronized (Agent.class) {
            if (isAlreadyStarted()) {
                System.err.println("------------------------------");
                System.err.println("agent started twice");
                System.err.println("------------------------------");
                return;
            }

            setAlreadyStarted(true);
        }


        System.err.println("------------------------------");
        System.err.println("agent started, Build: " + MavenBuildInfo.VERSION);


        try {


            System.err.println("-javaagent:" + Agent.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

            File agentFile = new File(Agent.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            String libPath = agentFile.toString().substring(0, agentFile.toString().length() - "/agent.jar".length());
            String configPath = args;
            List<URL> urlList = new LinkedList<URL>();
            File dir = new File(libPath);

            for (File file : dir.listFiles()) {

                if (file.getName().equals("agent_runtime.jar")) {
                    urlList.add(file.toURI().toURL());
                }
                if (file.getName().equals("agent_bootstrap.jar")) {
                    urlList.add(file.toURI().toURL());
                }


            }
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            URLClassLoader classloader = new URLClassLoader(urlList.toArray(new URL[]{}), null);
            Thread.currentThread().setContextClassLoader(classloader);
            String agentName = "com.vmlens.nottraced.agent.AgentRuntimeImpl";
            AgentRuntime agentRuntime = (AgentRuntime) classloader.loadClass(agentName).newInstance();
            agentRuntime.run(configPath, inst);

            System.err.println("------------------------------");

            Thread.currentThread().setContextClassLoader(contextClassLoader);

        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
