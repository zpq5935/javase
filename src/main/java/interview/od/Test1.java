package interview.od;


import java.util.HashMap;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Test1 Test1 = new Test1();
        Test1.Config config = new Test1.Config();
        config.serviceType = ServiceType.IAAS;
        Test1.init();
        Test1.deploy(config);
        //
        config.serviceType = ServiceType.PAAS;
        Test1.deploy(config);

    }

    Map<Test1.ServiceType, Test1.DeployI> mapping = new HashMap<>();


    public void deploy(Test1.Config config) throws IllegalAccessException, InstantiationException {
        Test1.DeployI service = mapping.get(config.serviceType);
//        DeployI service = loadService();
        service.deploy();
    }

    public Test1.DeployI loadService(String serviceName) {

        return new Test1.AImpl();
    }

    public void init() {
        mapping.put(Test1.ServiceType.IAAS, new Test1.AImpl());
        mapping.put(Test1.ServiceType.SAAS, new Test1.BImpl());
        mapping.put(Test1.ServiceType.PAAS, new Test1.CImpl());
//        mapping.put(ServiceType.SAAS, B.class.getName());
//        mapping.put(ServiceType.PAAAS, C.class.getName());
    }

    static class Config {
        String pkgs;
        String infos;
        Test1.ServiceType serviceType;
    }

    enum ServiceType {
        PAAS, IAAS, SAAS
    }


    interface DeployI {
        void deploy();
    }


    class AImpl implements Test1.DeployI {
        @Override
        public void deploy() {
            System.out.println("A");
        }
    }


    class BImpl implements Test1.DeployI {
        @Override
        public void deploy() {
            System.out.println("B");
        }
    }


    class CImpl implements Test1.DeployI {
        @Override
        public void deploy() {
            System.out.println("C");
        }
    }
}
