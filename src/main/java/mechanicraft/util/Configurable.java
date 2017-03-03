package mechanicraft.util;

public interface Configurable
{
	void onNewConfig(DataCluster cc);
	void onReadConfig();
	DataCluster getConfiguration();
	String getCodeName();
}
