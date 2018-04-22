package clustering4ever.clustering

import _root_.clustering4ever.math.distances.Distance
import _root_.scala.collection.mutable
import _root_.org.apache.spark.rdd.RDD

/**
 * @author Beck Gaël
 **/
abstract class CommonRDDPredictClusteringModel[T](centers: mutable.HashMap[Int, T], metric: Distance[T]) extends CommonPredictClusteringModel(centers, metric)
{
	/**
	 * Time complexity O(n<sub>data</sub>.c) with c the number of clusters
	 * @return the input RDD with labels obtain via centerPredict method
	 **/
	def centerPredict(data: RDD[T]): RDD[(ClusterID, T)] =
	{
		data.map( v => (centerPredict(v), v) )
	}
	/**
	 * Time complexity O(n<sub>data</sub>.n<sub>trainDS</sub>)
	 * @return the input RDD with labels obtain via knnPredict method
	 **/
	def knnPredict(data: RDD[T], k: Int, trainDS: Array[(ClusterID, T)]): RDD[(ClusterID, T)] =
	{
		data.map( v => (knnPredict(v, k, trainDS), v) )
	}
}