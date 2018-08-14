package clustering4ever.math.distances

import scala.collection.immutable

/**
 * @author Beck Gaël
 * Most general notion of Distance, taking two object of type T and returning a Double
 **/
trait Distance[T] extends Serializable
{
	def d(obj1: T, obj2: T): Double
}

trait ContinuousDistances extends Distance[immutable.Seq[Double]]
{
	def d(vector1: immutable.Seq[Double], vector2: immutable.Seq[Double]): Double
}

trait BinaryDistance[T <: immutable.Traversable[Int]] extends Distance[T]
{
	def d(vector1: T, vector2: T): Double
}

trait BinaryDistanceSeq extends BinaryDistance[immutable.Seq[Int]]
{
	def d(vector1: immutable.Seq[Int], vector2: immutable.Seq[Int]): Double
}

trait BinaryDistanceVector extends BinaryDistance[immutable.Vector[Int]]
{
	def d(vector1: immutable.Vector[Int], vector2: immutable.Vector[Int]): Double
}
