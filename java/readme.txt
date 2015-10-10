Generator of test input can be lauched with maven:

mvn -q exec:java -Dexec.mainClass=net.cristcost.dada.dailyreport.utils.GeneratorLauncher -Dexec.classpathScope=test -Dexec.args='<options>'

With no argument, usage is printed.

Example: -Dexec.args='-B2048 -H2000 1000000 test.log'