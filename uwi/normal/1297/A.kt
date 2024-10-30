import java.io.PrintWriter
import java.util.*

fun solve() {
    var t = ni()
    for(i in 1..t){
        var n = ni()
        if(n <= 999){
            out.println(n)
        }else if(n < 999500){
            out.print((n+500)/1000)
            out.println("K")
        }else{
            out.print((n+500000)/1000000)
            out.println("M")
        }
    }
}

val out = PrintWriter(System.out)

fun main(args: Array<String>) {
    solve()
    out.flush()
}

fun nline() = readLine()!!
fun ni() = nline().toInt()
fun nl() = nline().toLong()
fun nd() = nline().toDouble()
fun nas() = nline().split(" ")
fun na() = nas().map { it.toInt() }
fun nal() = nas().map { it.toLong() }

fun tr(x: Any) = System.err.println(x)
fun tr(x: IntArray) = System.err.println(Arrays.toString(x))
fun tr(x: Array<Any>) = System.err.println(Arrays.deepToString(x))