package output;

import input.ConsumerInp;
import input.DistributorInp;
import input.Input;
import input.ProducerInp;
import utils.Contract;

import java.util.ArrayList;
import java.util.List;

public final class End {
    private static End instance = null;

    private End() { }

    /**
     * Method called to obtain the Singleton instance
     * @return the instance
     */
    public static End getInstance() {
        if (instance == null) {
            instance = new End();
        }
        return instance;
    }

    /**
     * Method called to create the output
     * @param input the input the data is taken from
     * @return the output
     */
    public Output gameEnd(final Input input) {
        Output output = new Output();
        List<PlayerOut> consumers = new ArrayList<>();
        List<PlayerOut> distributors = new ArrayList<>();
        List<PlayerOut> producers = new ArrayList<>();
        OutFactory outFactory = OutFactory.getInstance();

        for (ConsumerInp c : input.getInitialData().getConsumers()) {
            PlayerOut consumerOut = outFactory.createOut(OutFactory.OutType
                    .valueOf("consumer"), c);
            consumers.add(consumerOut);
        }

        for (DistributorInp d : input.getInitialData().getDistributors()) {
            for (Contract c : d.getContractsInput()) {
                ContractOut contractOut = new ContractOut(c);
                d.getContracts().add(contractOut);
            }
            PlayerOut distributorOut = outFactory.createOut(OutFactory
                    .OutType.valueOf("distributor"), d);
            distributors.add(distributorOut);
        }

        for (ProducerInp p : input.getInitialData().getProducers()) {
            PlayerOut producerOut = outFactory.createOut(OutFactory.OutType
                    .valueOf("producer"), p);
            producers.add(producerOut);
        }

        output.setConsumers(consumers);
        output.setDistributors(distributors);
        output.setEnergyProducers(producers);

        return output;
    }
}
