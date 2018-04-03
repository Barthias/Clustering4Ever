package clustering4ever.clustering

import clustering4ever.clustering.datasetstype.DataSetsTypes

/**
 * @author Beck Gaël
 **/
trait ClusteringAlgorithms[IDNature, NaturesValue] extends DataSetsTypes[IDNature, NaturesValue]
{
	/**
	 * Execute the corresponding clustering algorithm
	 * @return ClusteringModel
	 **/
	def run(): ClusteringModel
}