package vn.com.lol.repilcatedb.configs.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager
                .isCurrentTransactionReadOnly() ?
                DataSourceType.READ_ONLY :
                DataSourceType.READ_WRITE;
    }
}
