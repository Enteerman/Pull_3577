package me.enterman.pull3577

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole


open class Benchmark {
    @State(Scope.Thread)
    class RandomStringsState {
        final val setExtensions = HashSet<String>()
        final val arrayExtensions: Array<String>
        final val fileName: String
        private fun randomString(charset: Collection<Char>, sizeRange: IntRange) = CharArray(sizeRange.random()){charset.random()}
        init {
            val charset = ('a'..'z') + ('A'..'Z')
            val alphanumeric = charset + ('0'..'9')
            val tempArray = arrayOfNulls<String>(100)
            repeat(100) { idx ->
                val str = randomString(charset, 3..8).concatToString()
                setExtensions.add(str)
                tempArray[idx] = ".$str"
            }
            arrayExtensions = tempArray.requireNoNulls()
            fileName = buildString {
                append(randomString(alphanumeric, 5..20)).append('.').append(randomString(charset, 3..8))
            }
        }
    }

    private fun CharSequence.endsWithAny(arrayStrings: Array<String>) =
        arrayStrings.any { this.startsWith(it, ignoreCase = true) }

    @Benchmark
    fun endsWithAny(state: RandomStringsState, blackhole: Blackhole) {
        val result = state.fileName.endsWithAny(state.arrayExtensions)
        blackhole.consume(result)
    }

    @Benchmark
    fun substringAfterLastHashSet(state: RandomStringsState, blackhole: Blackhole) {
        val result = state.fileName.substringAfterLast('.', "") in state.setExtensions
        blackhole.consume(result)
    }
}