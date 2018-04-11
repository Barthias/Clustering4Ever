package clustering4ever.spark.clustering.gaussianmixture

import _root_.clustering4ever.clustering.ClusteringModel
import _root_.scala.collection.mutable
import _root_.clustering4ever.math.distances.ContinuousDistances
import _root_.clustering4ever.clustering.datasetstype.DataSetsTypes
import _root_.org.apache.spark.rdd.RDD

/**
 * @author Beck Gaël
 **/
class GaussianMixtureModel(val centers: mutable.HashMap[Int, Array[Double]], val cardinalities: mutable.HashMap[Int, Int], val metric: ContinuousDistances, val finalAffectation: RDD[(Int, Array[Double])]) extends ClusteringModel with DataSetsTypes[Int, Array[Double]]
{
	val centersAsArray = centers.toArray
	/**
	 * Return the nearest mode for a specific point
	 **/
	def predict(v: Array[Double]): ClusterID = {
		centersAsArray.map{ case(clusterID, centroid) => (clusterID, metric.d(centroid, v)) }.sortBy(_._2).head._1
	}
	/**
	 * Return the nearest mode for a dataset
	 **/
	def predict(data: RDD[Array[Double]]): RDD[(ClusterID, Vector)] = {
		data.map( v => (predict(v), v) )
	}
}