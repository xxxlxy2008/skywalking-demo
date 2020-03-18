package com.xxx;

import java.util.List;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class AttachMain {
    public static void main(String[] args) throws Exception {
        List<VirtualMachineDescriptor> listBefore =
             VirtualMachine.list();
        String jar = "/Users/xxx/SW/skywalking-demo/TestAgent/target/test-agent.jar"; // agentmain()方法所在jar包
        VirtualMachine vm = null;
        List<VirtualMachineDescriptor> listAfter = null;
        while (true) {
            listAfter = VirtualMachine.list();
            for (VirtualMachineDescriptor vmd : listAfter) {
                if (!listBefore.contains(vmd)) { // 发现新的JVM
                    vm = VirtualMachine.attach(vmd); // attach到新JVM
                    vm.loadAgent(jar); // 加载agentmain所在的jar包
                    vm.detach(); // detach
                    return;
                }
            }
            Thread.sleep(1000);
        }
    }
}