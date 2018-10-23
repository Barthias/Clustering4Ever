package clustering4ever.scala.kernels
/**
 * @author Beck Gaël
 */
import scala.reflect.ClassTag
import clustering4ever.math.distances.Distance
import clustering4ever.math.distances.scalar.Euclidean
import clustering4ever.scala.kernels.KernelNature._
/**
 * Class regrouping arguments value for a specific kernel type
 */
class KernelArgs[O, D <: Distance[O]](
	val kernelType: KernelType,
	val bandwidth: Option[Double] = None,
	val metric: Option[D] = None,
	val k: Option[Int] = None,
	val a: Double = 1D,
	val b: Double = 0D,
	val lambda: Double = 1D
)