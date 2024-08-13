# **UT9.3. STREAMS**

Un Stream és una **seqüència o successió de dades** (NO una col·lecció NI una estructura de dades) sobre les quals es pot fer una sèrie d'operacions, que poden anar encadenades (chaining) fins a donar un resultat final. 

<img src="UT9.3. Streams_images/image_1.png" alt="Image 1">

Estes operacions realitzades amb els Stream poden ser de dos tipus:

* **Intermèdies**: Filtren o transformen la seqüència de dades. Donen com a resultat un nou **stream** al qual li podem seguir aplicant noves operacions (operacions pendents)  
* **Terminals**: Finalitzen el processament (efectúa l’operació resultant de les intermèdies). Retornen un valor o realitzen alguna acció sobre les dades.


Les col·leccions en Java ofereixen mètodes per a passar a streams dels objectes que contenen (mètodes stream i parallelStream). Així, si tenim un ArrayList anomenat “usuaris” que conté objectes de tipus “Usuari” podríem fer:

Stream\<Usuari\> fluxUsuaris \= usuaris.stream();

La idea és crear, a partir d'una col·lecció o taula (Map), o bé explícitament, un Stream al qual s'apliquen operacions intermèdies encadenades (el que es coneix com una canonada o pipeline), obtenint un resultat final per mitjà d'una operació terminal.

Un primer avantatge que podem veure als Stream (més endavant en veurem més) és que disposa de moltes més operacions per processar les dades que les col·leccions o les taules.

Els Stream són objectes que implementen la **interfície Stream**. Per tant, la classe Stream no existeix i els objectes Stream no es poden crear amb un constructor sinó només invocant certs mètodes que retornen un Stream.  
S'anomenen “operacions agregades” aquelles que operen sobre la totalitat d'un Stream, permetent l'execució en paral·lel, transparent al programador, per augmentar la velocitat del procés (imagina càlculs o cerques sobre matrius).

# Característiques d'un Stream

* Les operacions intermèdies retornen un Stream (encadenament).   
* Les operacions intermèdies es posen en cua i són invocades en executar una operació terminal (s’invoca la composició de les intermèdies).   
* Només es pot recórrer o consumir una vegada.   
* Iteració interna vs. externa: ens centrem en què fer amb les dades, no en com recórrer-les.

# Subtipus bàsics

* **IntStream**: Stream d’elements “int” (ja que sabem que no es pot fer Stream\<int\>)  
* **LongStream**: Stream d’elements “long”  
* **DoubleStream**: Stream d’elements “double”

# Formes de crear un Stream

Hi ha diverses maneres d'obtenir un Stream inicial, és a dir, que no procedisca d'un altre Stream. En veiem algunes:

* A partir d'una col·lecció: invocant al mètode “stream()”, definit a les classes de tipus Collection.  
  Stream\<T\> nomStream \= nomColeccio.stream();  
* A partir d'un array de tipus T\[ \]: usant el mètode of() de la interfície Stream, amb l’array com a paràmetre d'entrada.  
  Stream\<T\> nomStream \= Stream.of(T\[\] array);  
* A partir d'una taula de tipus T\[ \]: usant el mètode “stream()” de la classe Arrays, amb la taula com a paràmetre (utilitza este mètode si T representa un tipus primitiu)  
  Stream\<T\> nomStream \= Arrays.stream(T\[\] array);  
* Inicialitzant-lo directament: també amb el mètode “of()” però passant-li com a llista de paràmetres els valors de tipus T que l'inicialitzen  
  Stream\<T\> nomStream \= Stream.of(T v1, T v2, T v3, …);  
* Iterant sobre un valor inicial amb el mètode “iterate()” de Stream tornarà un stream infinit, ordenat i seqüencial  
  Stream\<T\> nomStream \= Stream.iterate(T i, UnaryOperator\<T\> o);  
* També podem fer que no siga infinit definint la condició d’iteració amb iterate:  
  Stream\<T\> nomStream \= Stream.iterate(T i, Predicate\<? super T\> p, UnaryOperator\<T\> o);  
* A partir d'un Supplier tornarà un Stream infinit, seqüencial i no ordenat  
  Stream\<T\> nomStream \= Stream.generate(Supplier\<T\> s);

<img src="UT9.3. Streams_images/image_2.png" alt="Image 2">

El Stream localitats **conté una còpia de totes les dades de la llista, no una referència** als originals. Per tant els canvis que es facen al Stream no es reflecteixen a la llista original que queda intacta.

També podem crear IntStream fent un rang  obert o bé tancat:

IntStream xifresPositives \= IntStream.range(1, 10);  
o bé  
IntStream xifresPositives \= IntStream.rangeClosed(1, 9);

Algunes classes de l'API de java tenen mètodes que tornen Stream o algun dels seus derivats (IntStream, DoubleStream…) a destacar per exemple la classe Random:

// Genera un stream de 100 enters aleatoris entre 0 i 10  
IntStream aleatoris \= new Random().ints(100, 0, 10);

Un altre exemple, la classe String ens ofereix “chars()” que genera un IntStream amb tots els seus caràcters (en format enter).

<img src="UT9.3. Streams_images/image_3.png" alt="Image 3"><img src="UT9.3. Streams_images/image_4.png" alt="Image 4">  
En realitat no necessitem crear les variables:  
"Hola món\!".chars().forEach(...)

*Prova el mètode “tokens()” de la classe Scanner.*

# Opcions de filtratge

* filter(Predicate\<T\>): ens permet filtrar utilitzant una condició.  
* limit(n): ens permet obtenir els n primers elements.  
* skip(m): ens permet obviar els m primers elements.  
* distinct: descarta els elements repetits (segons marque “equals”)

## Filter

<img src="UT9.3. Streams_images/image_5.png" alt="Image 5">

* Operació intermèdia  
* Ens permet eliminar aquells elements del stream que no compleixen una determinada condició  
* Condició com a Predicate.  
* Molt combinable amb findFirst, findAny  
* Combinable amb la resta de mètodes intermedis i terminals.

<img src="UT9.3. Streams_images/image_6.png" alt="Image 6">

<img src="UT9.3. Streams_images/image_7.png" alt="Image 7">

<img src="UT9.3. Streams_images/image_8.png" alt="Image 8">

<img src="UT9.3. Streams_images/image_9.png" alt="Image 9">

## Limit

* Operació intermèdia.  
* Ens permet limitar el nombre d'elements que contindrà el stream.  
* Passem com a paràmetre el nombre d'elements que tindrà el stream resultant.


<img src="UT9.3. Streams_images/image_10.png" alt="Image 10">  
<img src="UT9.3. Streams_images/image_11.png" alt="Image 11">

## Skip

* Operació intermèdia.  
* Este mètode pren un número N com a argument i torna una seqüència (stream) després d'eliminar els primers N elements


<img src="UT9.3. Streams_images/image_12.png" alt="Image 12">

## Distinct

* Operació intermèdia.  
* Este mètode elimina els elements duplicats i retorna el Stream sense duplicats.


<img src="UT9.3. Streams_images/image_13.png" alt="Image 13">

## Diferències?

<img src="UT9.3. Streams_images/image_14.png" alt="Image 14">  
<img src="UT9.3. Streams_images/image_15.png" alt="Image 15">  
<img src="UT9.3. Streams_images/image_16.png" alt="Image 16">

# Concatenació d’Streams. Concat

El mètode Stream.concat() crea un flux concatenat en què els elements són tots els elements del primer flux seguits de tots els elements del segon flux. El flux resultant s'ordena si els dos fluxos d'entrada estan ordenats i és paral·lel si qualsevol flux d'entrada és paral·lel.

<img src="UT9.3. Streams_images/image_17.png" alt="Image 17">

<img src="UT9.3. Streams_images/image_18.png" alt="Image 18">

<img src="UT9.3. Streams_images/image_19.png" alt="Image 19">

També es poden concatenar streams de tipus derivat:

<img src="UT9.3. Streams_images/image_20.png" alt="Image 20">

# Ordenació de dades. Sorted

* Operació intermèdia.  
* Ens permet ordenar els elements d'un stream.  
* Recordem que per ordenar qualsevol conjunt d'objectes d'una classe necessitem en primer lloc o com a punt de partida que implemente l'interfície Comparable\<T\>. També es pot passar com a paràmetre un Comparator\<T\>

<img src="UT9.3. Streams_images/image_21.png" alt="Image 21">  
<img src="UT9.3. Streams_images/image_22.png" alt="Image 22">  
<img src="UT9.3. Streams_images/image_23.png" alt="Image 23">  
<img src="UT9.3. Streams_images/image_24.png" alt="Image 24">

Altres maneres d'usar el mètode “sorted”  
<img src="UT9.3. Streams_images/image_25.png" alt="Image 25"><img src="UT9.3. Streams_images/image_26.png" alt="Image 26">  
<img src="UT9.3. Streams_images/image_27.png" alt="Image 27">

# Transformació de dades

## Map

<img src="UT9.3. Streams_images/image_28.png" alt="Image 28">

* Operació intermèdia molt utilitzada.  
* Permet aplicar una transformació a una sèrie de dades.  
* Retorna un stream resultat de la correspondència de cada  
  element de la seqüència original transformat en altra dada.  
* Accepta com a paràmetre una funció (Function\<T, R\>)

<img src="UT9.3. Streams_images/image_29.png" alt="Image 29">  
<img src="UT9.3. Streams_images/image_30.png" alt="Image 30">  
<img src="UT9.3. Streams_images/image_31.png" alt="Image 31">  
<img src="UT9.3. Streams_images/image_32.png" alt="Image 32">  
<img src="UT9.3. Streams_images/image_33.png" alt="Image 33">

## MapToInt i MapToDouble

Hi ha dos mètodes addicionals orientats a treballar amb dades numèriques. Aquests mètodes són **mapToInt** i **mapToDouble**. Si canviem el nostre mètode de map a mapToInt o mapToDouble (quan sapiem que la transformació és a un enter o un double primitius) se'ns obrirà la possibilitat d'accedir a mètodes addicionals orientats a estadístiques.

<img src="UT9.3. Streams_images/image_34.png" alt="Image 34">

Els mètodes **sum, average, max i min són TERMINALS** (igual que foreach) per la qual cosa tornen un resultat i no un altre stream (fins que no troba una operació terminal realment no s’executa cap intermèdia). En el cas de “average”, “max” i “min” retornen un **Optional**.

<img src="UT9.3. Streams_images/image_35.png" alt="Image 35">  
<img src="UT9.3. Streams_images/image_36.png" alt="Image 36">  
<img src="UT9.3. Streams_images/image_37.png" alt="Image 37">

## MapToObj

Una altra de les operacions intermèdies que podem fer és mapToObj sobre un IntStream / LongStream / DoubleStream. Este mètode retorna un Stream amb valor d'objecte que consta dels resultats d'aplicar la funció donada. Per exemple, imaginem que a partir d'una llista d'enters volem convertir-los a un String del seu valor en hexadecimal:

<img src="UT9.3. Streams_images/image_38.png" alt="Image 38">

Vegem per exemple com convertir un Stream de doubles a un d'Angles:

<img src="UT9.3. Streams_images/image_39.png" alt="Image 39">

(\*) També podem aplicar mètodes map, mapToInt, mapToDouble o MapToLong…  
 

## FlatMap

<img src="UT9.3. Streams_images/image_40.png" alt="Image 40">

Els streams sobre col·leccions d'un nivell (com List) es poden transformar (map) fàcilment. Però què passa si tenim una col·lecció que inclou dins una altra?

<img src="UT9.3. Streams_images/image_41.png" alt="Image 41">

<img src="UT9.3. Streams_images/image_42.png" alt="Image 42">

Per a vore totes les destinacions, amb estil imperatiu “for” niuem dos bucles:

<img src="UT9.3. Streams_images/image_43.png" alt="Image 43">  
▸ Podem observar bé per adonar-nos dels tipus de retorn dels mètodes intermedis:

<img src="UT9.3. Streams_images/image_44.png" alt="Image 44">  
▸ Necessitem un mètode que unifique totes les llistes de viatges en un sol Stream:   
▸ Eixa és la funcionalitat de flatMap. 

Amb flatMap no només apliquem una transformació, sino que passen a un sol Stream: 

<img src="UT9.3. Streams_images/image_45.png" alt="Image 45">

Imaginem que volem obtenir la suma de tots els dies que han estat de vacances els nostres turistes en tots els seus viatges:  
<img src="UT9.3. Streams_images/image_46.png" alt="Image 46">  
També tenim les versions primitives (flatMapToInt, …): 

<img src="UT9.3. Streams_images/image_47.png" alt="Image 47">

Amb això podem deduir altres formes per concatenar Streams. Si recordem anteriorment hem vist l'exemple  
<img src="UT9.3. Streams_images/image_17.png" alt="Image 17">

Amb flatMap podem aconseguir el mateix efecte sense haver de niar funcions concat.

<img src="UT9.3. Streams_images/image_48.png" alt="Image 48">

# Reducció de dades. Reduce

<img src="UT9.3. Streams_images/image_49.png" alt="Image 49">

▸ reduce(BinaryOperator\<T\> accumulator): Optional\<T\>   
Realitza la reducció del Stream usant una funció associativa. Retorna un Optional 

▸ reduce(T identity, BinaryOperator\<T\> accumulator): T   
Realitza la reducció usant un valor inicial i una funció associativa. Retorna un valor com a resultat. 

Redueix, la qual cosa ens permet obtindre un únic resultat partint d'un stream. Operació terminal.

Per a poder aconseguir el nostre resultat únic final, farem ús dels paràmetres següents:

* **Identitat (Identity)**: És el valor inicial del procés. \[Paràmetre opcional. Si no es proporciona este paràmetre el mètode “reduce” retorna un Optional\]  
* **Acumulador (Accumulator)**: És l'encarregat de processar els valors i acumular-ne el resultat.  
* **Combinador (Combiner)**: El combinador és usat per combinar el resultat parcial obtingut quan la reducció és paral·lelitzada. \[Paràmetre opcional\]

Aquí veiem el funcionament invocant al primer constructor de “reduce” (sense Identity)

Més habitual és utilitzar un primer paràmetre com a identitat o valor inicial de la reducció.

<img src="UT9.3. Streams_images/image_50.png" alt="Image 50">

<img src="UT9.3. Streams_images/image_51.png" alt="Image 51">

Com podem imprimir la suma de dies de tots els viatges efectuats pels turistes? I la suma de dies de cada turista?

<img src="UT9.3. Streams_images/image_52.png" alt="Image 52">  
<img src="UT9.3. Streams_images/image_53.png" alt="Image 53">

Analitza què imprimeix el codi següent:

<img src="UT9.3. Streams_images/image_54.png" alt="Image 54">

Amb això podem deduir altres formes per concatenar Streams. Amb reduce podem aconseguir el mateix efecte sense haver de niar funcions concat:

<img src="UT9.3. Streams_images/image_55.png" alt="Image 55">

# Operacions terminals

Fins ara la majoria d'operacions vistes són intermèdies (generen un altre stream). Un exemple d'operació terminal seria “**reduce**” o “**forEach**” que ja no tornen un altre stream.  
També hem vist altres operacions terminals com ara **sum**, **average**, **max** i **min** (en este cas només per als IntStream o DoubleStream).

* **long count()**  
  Retorna un enter llarg amb el nombre d'elements que conté el *Stream*.  
* **Optional\<T\> findFirst()**  
  Retorna el primer element del Stream, o un Optional buit si no hi ha cap element.  
* **Optional\<T\> findAny()**  
  Retorna un element qualsevol del *Stream* o un Optional buit si no hi ha cap element.  
* **boolean allMatch(predicat)**  
  Retorna *true* si la condició del predicat es compleix per a tots els elements del Stream, *false* per a la resta de casos.  
* **boolean anyMatch(predicat)**  
  Retorna *true* si la condició del predicat la compleix almenys un element del Stream, *false* per a la resta de casos.  
* **boolean noneMatch(predicat)**  
  Retorna *true* si la condició del predicat no es compleix per a cap element del Stream, *false* per a la resta de casos.

**<img src="UT9.3. Streams_images/image_56.png" alt="Image 56">**

<img src="UT9.3. Streams_images/image_57.png" alt="Image 57">  
<img src="UT9.3. Streams_images/image_58.png" alt="Image 58">  
<img src="UT9.3. Streams_images/image_59.png" alt="Image 59">  
<img src="UT9.3. Streams_images/image_60.png" alt="Image 60">  
<img src="UT9.3. Streams_images/image_61.png" alt="Image 61">  
<img src="UT9.3. Streams_images/image_62.png" alt="Image 62">  
<img src="UT9.3. Streams_images/image_63.png" alt="Image 63">  
<img src="UT9.3. Streams_images/image_64.png" alt="Image 64">

**// EXERCICIS 1-15 .** 

# Recol·lectors

Fins ara, les operacions realitzades amb streams han acabat amb una eixida per consola.

I si volem transformar un stream (immutable) i guardar-ne el resultat en una col·lecció (mutable)? Operació collect.

Java SE 8 introdueix **Collectors**, amb mètodes estàtics molt usuals i pràctics al paquet java.util.stream.Collectors.\*;

Ens permet realitzar algun tipus d'operació i recol·lectar el valor en un sol (un sol valor, una sola col·lecció…).

Alguns es solapen amb operacions finals que ja hem vist; poden utilitzar-se juntament amb altres recol·lectors.

Alguns dels recol·lectors bàsics són:

▸ **counting:** compta el nombre d'elements.  
▸ **minBy, maxBy**: obté el mínim o màxim segons un comparador.  
▸ **summingInt, summingLong, summingDouble**: la suma dels elements (segons el tipus).  
▸ **averagingInt, averagingLong, averagingDouble**: la mitjana (segons el tipus).  
▸ **summarizingInt, summarizingLong, summarizingDouble**: els valors anteriors,  agrupats en un objecte de resum d’estadístiques (segons el tipus).  
▸ **joining**: unió dels elements en una cadena de text.

## A “Collection”

* Produeixen com a resultat una de les col·leccions ja conegudes: List i Set  
* També pot produir altres estructures de dades de tipus key-value com Map.

Al següent exemple convertim el “steam” en una llista modificable:

<img src="UT9.3. Streams_images/image_65.png" alt="Image 65">

*(Utilitza **Collectors.toList()** si no has importat la classe “Collectors” al teu projecte)*

També podem fer-ho en una llista no modificable.

<img src="UT9.3. Streams_images/image_66.png" alt="Image 66">

*(\*) A partir de Java 16 s’afegeix el mètode “toList()” a la classe “Stream” (sense fer ús de “collect”) que simplifica molt la nostra tasca: [Collecting Stream Items into List in Java](https://howtodoinjava.com/java8/convert-stream-to-list/\#11-streamtolist)*

*<img src="UT9.3. Streams_images/image_67.png" alt="Image 67">*

També podem recol·lectar les dades en un Set:

<img src="UT9.3. Streams_images/image_68.png" alt="Image 68">  
<img src="UT9.3. Streams_images/image_69.png" alt="Image 69">

Si volem utilitzar una implementació personalitzada (ArrayList, LinkedList, HashSet…), necessitarem utilitzar el recol·lector **toCollection** amb una col·lecció proporcionada de la nostra elecció.

<img src="UT9.3. Streams_images/image_70.png" alt="Image 70">

<img src="UT9.3. Streams_images/image_71.png" alt="Image 71">

## A “Map”

El recol·lector “toMap” es pot utilitzar per a recopilar elements Stream en una instància de “map”. Per a fer-ho, necessitem proporcionar dos funcions:

	keyMapper  
	valueMapper

Usarem keyMapper per a extreure una clau de mapa d'un element Stream i valueMapper per extreure un valor associat amb una clau determinada.

Per exemple si volem guardar els elements en un “map” que tinga cadenes com a claus i les seues longituds com a valors:

<img src="UT9.3. Streams_images/image_72.png" alt="Image 72">

També podem, a partir d'un Stream de persones generar un “map” la clau del qual siga el dni i el seu valor siga el seu nom:

<img src="UT9.3. Streams_images/image_73.png" alt="Image 73">

toMap NO avalua si els valors clau són iguals. Si troba claus duplicades, llança immediatament una IllegalStateException.

En estos casos amb col·lisió de claus, hauríem d’utilitzar toMap amb un tercer paràmetre que indica què fer en cas de col·lisions:

<img src="UT9.3. Streams_images/image_74.png" alt="Image 74">

## Recol·lecta i llavors…

**CollectingAndThen** és un recol·lector especial que ens permet realitzar una altra acció en un resultat, immediatament després què finalitza la recopilació.

<img src="UT9.3. Streams_images/image_75.png" alt="Image 75">  
<img src="UT9.3. Streams_images/image_76.png" alt="Image 76">

què mostrarà per pantalla?

<img src="UT9.3. Streams_images/image_77.png" alt="Image 77">

## joining

El recol·lector “joining” es pot utilitzar per unir elements Stream\<String\>.

Podem unir-los fent:

<img src="UT9.3. Streams_images/image_78.png" alt="Image 78">

També podem concatenar un String a principi i final de la cadena:

<img src="UT9.3. Streams_images/image_79.png" alt="Image 79">

Inclús podem mostrar una llista d’enters separats per comes.

<img src="UT9.3. Streams_images/image_80.png" alt="Image 80">

## counting

Counting és un recol·lector simple que permet comptar tots els elements de l’Stream. Ara podem escriure:

<img src="UT9.3. Streams_images/image_81.png" alt="Image 81">

## summarizingDouble/Long/Int.

<img src="UT9.3. Streams_images/image_82.png" alt="Image 82">

<img src="UT9.3. Streams_images/image_83.png" alt="Image 83">

Podem obtindre només la mitjana o només la suma dels valors:

<img src="UT9.3. Streams_images/image_84.png" alt="Image 84">  
<img src="UT9.3. Streams_images/image_85.png" alt="Image 85">  
<img src="UT9.3. Streams_images/image_86.png" alt="Image 86">

## maxBy / minBy

Els recol·lectors MaxBy/MinBy retornen l'element més gran o més xicotet d'un Stream segons una instància de Comparator proporcionada. 

<img src="UT9.3. Streams_images/image_87.png" alt="Image 87">

Obtenim la mínima persona si l'ordre és per edat:

<img src="UT9.3. Streams_images/image_88.png" alt="Image 88">  
Una altra manera:

<img src="UT9.3. Streams_images/image_89.png" alt="Image 89">

Podem vore que **retorna un “Optional”** qué serà “empty” si no troba cap valor.

## groupingBy

<img src="UT9.3. Streams_images/image_90.png" alt="Image 90">

El recol·lector GroupingBy s’utilitza per a agrupar objectes per alguna propietat i després emmagatzemar els resultats en una instància de Map. 

Per exemple podem agrupar-los per longitud de cadena:

<img src="UT9.3. Streams_images/image_91.png" alt="Image 91">

Es poden utilitzar recol·lectors bàsics, per a realitzar algun càlcul

<img src="UT9.3. Streams_images/image_92.png" alt="Image 92">  
<img src="UT9.3. Streams_images/image_93.png" alt="Image 93">

Es poden crear diversos nivells d'agrupament:

<img src="UT9.3. Streams_images/image_94.png" alt="Image 94">

Si ometem el toSet suposarà que els elements del grup van en una llista:

<img src="UT9.3. Streams_images/image_95.png" alt="Image 95">

## partitioningBy

<img src="UT9.3. Streams_images/image_96.png" alt="Image 96">

PartitioningBy és un cas especialitzat de groupingBy que accepta una instància de Predicate i després recopila elements de l’Stream en una instància de Map que emmagatzema valors booleans com a claus i col·leccions com a valors. En la clau "true", podem trobar com a valor una col·lecció d'elements que coincideixen amb el Predicat donat, i en la clau "false" podem trobar com a valor una col·lecció d'elements que no coincideixen amb el Predicat donat. 

#  <img src="UT9.3. Streams_images/image_97.png" alt="Image 97">

<img src="UT9.3. Streams_images/image_98.png" alt="Image 98">

<img src="UT9.3. Streams_images/image_99.png" alt="Image 99">

## mapping

De vegades pot passar que volem transformar els elements abans d'agrupar-los. Per això tenim la funció “mapping”.

Abans hem pogut agrupar persones segons la seua edat:

<img src="UT9.3. Streams_images/image_100.png" alt="Image 100">

Ens pot interessar agrupar per edat, però no fer grups de persones sinó simplement el seu nom. Per això podem fer:

<img src="UT9.3. Streams_images/image_101.png" alt="Image 101">

Recorda que amb **CollectingAndThen primer es recol·lecta**, i posteriorment s'aplica una funció al resultat, mentre que amb **mapping es fa just al contrari**, apliquem una funció a cada element abans de recol·lectar al grup.

Vegem un exemple on agrupem per edat i comptem quants elements hi ha en cada grup:

<img src="UT9.3. Streams_images/image_102.png" alt="Image 102">

El problema que se'ns pot plantejar és que volem desar el nombre d'ocurrències en format Integer, i no Long. Per això farem ús de collectingAndThen

<img src="UT9.3. Streams_images/image_103.png" alt="Image 103">

## filtering

Igual que amb mapping, de vegades pot passar que desitgem filtrar els elements abans de recol·lectar. Per això tenim la funció “filtering”. Abans hem agrupat per edat, “mapetjant” les persones a noms. Si a més volem filtrar els que tenen una longitud menor de 12 caràcters:

<img src="UT9.3. Streams_images/image_104.png" alt="Image 104">

## teeing

A partir de Java 12 tenim un recol·lector integrat que s'encarrega d'executar dos recol·lectors i combinar-lo. Tot el que hem de fer és proporcionar els dos recol·lectors i la funció combinadora.  
<img src="UT9.3. Streams_images/image_105.png" alt="Image 105">

## toArray

Hi ha vegades que no ens interessa recol·lectar les dades en una col·lecció sinó en un array clàssic. Per això disposem de la funció toArray, que es pot utilitzar de dos maneres  
<img src="UT9.3. Streams_images/image_106.png" alt="Image 106">

La primera i més senzilla simplement tornarà un array (de tipus Object o de tipus primitiu), amb els elements que conté l’stream:

<img src="UT9.3. Streams_images/image_107.png" alt="Image 107">  
<img src="UT9.3. Streams_images/image_108.png" alt="Image 108">

Si el que volem és recollir les dades en un array de tipus de dades complex (un objecte o array) llavors hem d'usar el segon mètode passant-li la funció generadora:

<img src="UT9.3. Streams_images/image_109.png" alt="Image 109">

Este generador també podria ser una funció lambda:

<img src="UT9.3. Streams_images/image_110.png" alt="Image 110">

# Combinant tot això…

Per a obtindre una llista amb els noms d’aquelles persones majors de 30 anys:

<img src="UT9.3. Streams_images/image_111.png" alt="Image 111">

Obtindre un “Map” que tinga com a clau el DNI i com a valor l'edat d’aquelles persones majors de 30 anys.

<img src="UT9.3. Streams_images/image_112.png" alt="Image 112">

Volem recol·lectar les persones per la màxima edat possible (una o cap persona) i que només recol·lecte el nom.

<img src="UT9.3. Streams_images/image_113.png" alt="Image 113">

Què obtindrem en este cas?

<img src="UT9.3. Streams_images/image_114.png" alt="Image 114">

I en este cas?

<img src="UT9.3. Streams_images/image_115.png" alt="Image 115">

A partir d’una matriu de 3 columnes i N files volem crear per a cada fila de la matriu un nou triangle. Després volem transformar cada triangle en un double que conginga l’àrea d’eixe triangle, i retornar una llista de Doubles amb les àrees calculades de tots els triangles.

**<img src="UT9.3. Streams_images/image_116.png" alt="Image 116">**  
I si tinguerem els costats en un array unidimensional?

<img src="UT9.3. Streams_images/image_117.png" alt="Image 117">  
Convertir el codi següent a l'estil funcional:  
<img src="UT9.3. Streams_images/image_118.png" alt="Image 118">

Solució 1:  
<img src="UT9.3. Streams_images/image_119.png" alt="Image 119">

Solució 2:  
<img src="UT9.3. Streams_images/image_120.png" alt="Image 120">

Solució 3  
<img src="UT9.3. Streams_images/image_121.png" alt="Image 121">

Solució 4  
<img src="UT9.3. Streams_images/image_122.png" alt="Image 122">

Solució 5:  
<img src="UT9.3. Streams_images/image_123.png" alt="Image 123">

Converteix la funció següent a l'estil funcional:

<img src="UT9.3. Streams_images/image_124.png" alt="Image 124">

Solució 1:  
<img src="UT9.3. Streams_images/image_125.png" alt="Image 125">

Solució 2:  
<img src="UT9.3. Streams_images/image_126.png" alt="Image 126">

Desenvolupa un mètode que a partir d'una llista d'Integers i un selector (predicat) ens retorne la suma de tots aquells elements que compleixen amb este selector.

<img src="UT9.3. Streams_images/image_127.png" alt="Image 127">

<img src="UT9.3. Streams_images/image_128.png" alt="Image 128">

**// EXERCICIS 16 \- 20**

# Extensió d’operacions

Podem crear noves operacions terminals utilitzant el mètode de fàbrica **java.util.stream.Collector.of(...)**; o amb l'ús dels col·lectors predefinits en **Collectors**, creant operacions terminals definides per l'usuari i reutilitzables.

De la mateixa manera podem crear noves operaciones intermèdies utilitzant els mètodes de fàbrica **java.util.stream.Gatherer.of(...)** i **java.util.stream.Gatherer.ofSequential(...)** o utilitzar els recopiladors predefinits en **Gatherers** (esta funció encara es troba en fase PREVIEW a la versió JDK22 de 2024\)

# Streams infinits

Com hem vist hi ha dues maneres de generar streams infinits, mitjançant els mètodes “generate” i “iterate”.

<img src="UT9.3. Streams_images/image_129.png" alt="Image 129">

El mètode iterate necessita un primer paràmetre com a seed (llavor) o element inicial i un segon paràmetre que serà un operador unari (una sola dada d'entrada del mateix tipus que seed al qual aplicarem alguna operació)

<img src="UT9.3. Streams_images/image_130.png" alt="Image 130">  
<img src="UT9.3. Streams_images/image_131.png" alt="Image 131">

D'altra banda la funció generate només necessita un paràmetre que serà de tipus Supplier (procediment generador):

<img src="UT9.3. Streams_images/image_132.png" alt="Image 132">

El proveïdor pot ser qualsevol generador que a partir de zero paràmetres d'entrada retorne un valor d'eixida (el cas més utilitzat són els aleatoris)

<img src="UT9.3. Streams_images/image_133.png" alt="Image 133">

# Avaluació mandrosa

Quan executem un càlcul una funció o una expressió, ho podem fer de dos maneres. La primera és de forma “ansiosa” (**eager**) el que suposa executar-la tan prompte com s'obté esta expressió en particular. Per contra, l'avaluació “mandrosa” (**lazy**) suposa posposar esta execució fins que puguem fer-la més endavant o fins al punt que ja no necessitem l'execució i la podem evitar. Per tant, un dels beneficis de ser mandrosos és precisament que podem ser més eficients.  
Analitzem el següent codi:  
<img src="UT9.3. Streams_images/image_134.png" alt="Image 134">

Java per defecte duu a terme una **avaluació ansiosa**, per la qual cosa executa el mètode “computar”, encara que després mai arribe a necessitar este valor (ja que a l'if s'incompleix l'operand esquerre i mai avalua el dret). 

Això parteix de la concepció imperativa i orientada a objectes, fet que fa al llenguatge “temer” per un possible efecte secundari per la qual cosa no pot posposar l'execució. 

Ara fixa’t en el següent exemple:  
<img src="UT9.3. Streams_images/image_135.png" alt="Image 135">

En este exemple NO s'executa el mètode “computar” perquè no cal. No obstant això, si assignem per exemple un 10 a la “x” apreciem que sí que s'executarà el mètode. Acabem de crear un **codi mandrós**.

Veurem un exemple amb col·leccions.

Versió tradicional:  
<img src="UT9.3. Streams_images/image_136.png" alt="Image 136">

Este codi presenta diversos problemes. Podria ser que en la col·lecció no tinguerem números més grans que 3, o no tinguerem parells o fins i tot que tinguerem una col·lecció buida. En tots estos casos el resultat seria 0, cosa que no és correcta. És un codi molt “familiar”, però molt poc declaratiu. Eliminem la mutabilitat i l'estil imperatiu.

Versió funcional:  
<img src="UT9.3. Streams_images/image_137.png" alt="Image 137">

Per tant, el següent codi no executa cap càlcul en no tenir operacions terminals:  
<img src="UT9.3. Streams_images/image_138.png" alt="Image 138">

Tot això ens permet utilitzar streams infinits ja que no es farà cap càlcul a priori. A continuació tenim un mètode al qual passem un enter “k” i un altre enter “n”. Haurà de retornar la suma total del doble dels primers “n” nombres parells començant per “k” i l'arrel quadrada de la qual (de cada número) siga més gran que 20\.

<img src="UT9.3. Streams_images/image_139.png" alt="Image 139">

# Paral·lelitzar

<img src="UT9.3. Streams_images/image_140.png" alt="Image 140">

L'objectiu del paral·lelisme és reduir el temps d'execució d'una tasca específica descomponent-la en components més xicotets que es duguen a terme en paral·lel. El paral·lelisme funciona molt bé quan volem aplicar la mateixa operació sobre un gran conjunt de dades.

Si tenim el següent mètode:

<img src="UT9.3. Streams_images/image_141.png" alt="Image 141">

Java farà el següent:  
<img src="UT9.3. Streams_images/image_142.png" alt="Image 142">

Vegem una comparació de temps:

<img src="UT9.3. Streams_images/image_143.png" alt="Image 143">

Seqüencial: **74**  
Paral·lel: **11**

Volem trobar la mitjana aritmètica dels divisibles entre 3 des del 10 al 1000000  
<img src="UT9.3. Streams_images/image_144.png" alt="Image 144">

Si treballem amb paral·lelisme desconeixem l'ordre en què s'executaran els resultats parcials:  
<img src="UT9.3. Streams_images/image_145.png" alt="Image 145"><img src="UT9.3. Streams_images/image_146.png" alt="Image 146">

Podem indicar “per a cada element ordenat” (forEachOrdered) de manera que abans de mostrar el resultat col·locarà cada element en la posició que li correspon.

<img src="UT9.3. Streams_images/image_147.png" alt="Image 147">

També hem de tenir en compte el paral·lelisme a l'hora de retornar el primer element (findFirst) o algun element que complisca les condicions (findAny):

<img src="UT9.3. Streams_images/image_148.png" alt="Image 148">

El resultat sempre serà “PERE” . Què passarà si en comptes de findFirst usem findAny?  
Si fem el stream paral·lel, el resultat de findFirst seguirà sent PERE en tots els casos.

<img src="UT9.3. Streams_images/image_149.png" alt="Image 149">

No obstant això, si usem “findAny” amb un stream paral·lel el resultat ja no serà predictible:

<img src="UT9.3. Streams_images/image_150.png" alt="Image 150">

En eixe últim cas “findAny” pot retornar Ana o Pere depenent del fil d’execució que el trobe abans (treballen en paral·lel).

Recordem el funcionament de la reducció:

<img src="UT9.3. Streams_images/image_151.png" alt="Image 151">

Si tenim un stream paral·lel recordem que es realitzaran les operacions parcials de forma paral·lela, i finalment es posaran en comú mitjançant una funció unidora o “combiner”. Esta funció “combiner” l'hem d'indicar com a tercer paràmetre del mètode “reduce”:

<img src="UT9.3. Streams_images/image_152.png" alt="Image 152">

Alguns exemples més per acabar:  
<img src="UT9.3. Streams_images/image_153.png" alt="Image 153">  
<img src="UT9.3. Streams_images/image_154.png" alt="Image 154">  
<img src="UT9.3. Streams_images/image_155.png" alt="Image 155">

**// EXERCICIS FINALS**

# Altres llenguatges

Per exemple en Kotlin, les col·leccions com les llistes o els conjunts ja disposen de mètodes   
com “filter”, “map”...

<img src="UT9.3. Streams_images/image_156.png" alt="Image 156">

<img src="UT9.3. Streams_images/image_157.png" alt="Image 157">

Si es vol treballar de forma mandrosa també disposa de “Sequence”:

<img src="UT9.3. Streams_images/image_158.png" alt="Image 158">

En JavaScript passa el mateix:

<img src="UT9.3. Streams_images/image_159.png" alt="Image 159">

# PER ACABAR. COSES A TINDRE EN COMPTE:

<img src="UT9.3. Streams_images/image_160.png" alt="Image 160">  
<img src="UT9.3. Streams_images/image_161.png" alt="Image 161">  
<img src="UT9.3. Streams_images/image_162.png" alt="Image 162">  
<img src="UT9.3. Streams_images/image_163.png" alt="Image 163">  
Usar peek és perillós. Només hem d’utilitzar-lo per a proves.