import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
	
	private static List<String> palavras = new ArrayList<String>();
	
	
	private static List<Curso> cursos = new ArrayList<Curso>();
	
	public static void main(String[] args) {
		
		palavras.add("Curso Teste 1");
		palavras.add("Novo Curso de Teste 2");
		palavras.add("Curso 3");
		
		cursos.add(new Curso("PHP", 45));
		cursos.add(new Curso("JavaScript", 150));
		cursos.add(new Curso("Java", 113));
		cursos.add(new Curso("C++", 55));
		
		formaNormal();
		formaComComparador();
		formaComComparadorNaLista();
		formaIterando();
		
		formaIterandoComClasseAnonima();
		formaIterandoComClasseAnonimaInterna();
		formaIterandoComClasseAnonimaSemCorpo();
		formaIterandoComClasseAnonimaSoComArgumento1();
		formaIterandoComClasseAnonimaSoComArgumento2();
		formaIterandoComClasseLambdaComImpressorConsumer();
		
		formaComMethodReference1();
		formaComMethodReference2();
		formaComMethodReference3();
		formaComMethodReference4();
		formaComMethodReference5();
		
		exemploCursos1();
		exemploCursos2();
		exemploCursos3();
		exemploCursos4();
		exemploCursos5();
		exemploCursos6();
		exemploCursos7();
		exemploCursos8();
		exemploCursos9();
		exemploCursos10();
		exemploCursos11();
		exemploCursos12();
		
		exemploDate1();
		exemploDate2();
		exemploDate3();
		exemploDate4();
		exemploDate5();
		exemploDate6();
		
		ThreadExempleLamba.exempleWithoutLambda("Bruno");
		ThreadExempleLamba.exempleWithLamba("Bruno");
	}
	
	public static void formaNormal() {
		Collections.sort(palavras);
		System.out.println(palavras);
		
		System.out.println("");
	}
	
	public static void formaComComparador() {
		Comparator<String> comparador = new ComparadorPorTamanho();
		
		Collections.sort(palavras, comparador);
		System.out.println(palavras);

		System.out.println("");
	}
	
	public static void formaComComparadorNaLista() {
		
		Comparator<String> comparador = new ComparadorPorTamanho();
		
		palavras.sort(comparador);
		System.out.println(palavras);
		
		System.out.println("");
	}
	
	public static void formaIterando() {
		Comparator<String> comparador = new ComparadorPorTamanho();
		
		palavras.sort(comparador);
		
		Consumer<String> consumidor = new ImprimeNaLinha();
		palavras.forEach(consumidor);
		
		System.out.println("");
	}
	
	public static void formaIterandoComClasseAnonima() {
		Comparator<String> comparador = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if(s1.length() < s2.length())
					return -1;
				if(s1.length() > s2.length())
					return 1;
				return 0;
			}
		};
		
		palavras.sort(comparador);
		
		Consumer<String> consumidor = new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		};
		
		palavras.forEach(consumidor);
		
		System.out.println("");
	}
	
	// Lambda
	public static void formaIterandoComClasseAnonimaInterna() {
		palavras.sort(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if(s1.length() < s2.length())
					return -1;
				if(s1.length() > s2.length())
					return 1;
				return 0;
			}
		});
		
		palavras.forEach(new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		});
		
		System.out.println("");
	}
	
	// Lambda
	public static void formaIterandoComClasseAnonimaSemCorpo() {
		palavras.sort((String s1, String s2) -> {
			if(s1.length() < s2.length())
				return -1;
			if(s1.length() > s2.length())
				return 1;
			return 0;
		});
		
		palavras.forEach((String s) -> {
			System.out.println(s);
		});
		
		System.out.println("");
	}
	
	// Lambda
	public static void formaIterandoComClasseAnonimaSoComArgumento1() {
		palavras.sort((String s1, String s2) -> {
			if(s1.length() < s2.length())
				return -1;
			if(s1.length() > s2.length())
				return 1;
			return 0;
		});
		
		palavras.forEach(s -> System.out.println(s));
		
		System.out.println("");
	}
	
	// Lambda
	public static void formaIterandoComClasseAnonimaSoComArgumento2() {
		palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
		
		palavras.forEach(s -> System.out.println(s));
		
		System.out.println("");
	}
	
	// Lambda
	public static void formaIterandoComClasseLambdaComImpressorConsumer() {
		Consumer<String> impressor = s -> System.out.println(s);
		palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
		
		palavras.forEach(impressor);
		palavras.forEach(s -> System.out.println(s));
		
		System.out.println("");
	}
	
	// Method Reference
	public static void formaComMethodReference1() {
		Function<String, Integer> funcao = new Function<String, Integer>() {
			@Override
			public Integer apply(String s) {
				return s.length();
			}
		};
		
		Comparator<String> comparador = Comparator.comparing(funcao);
		palavras.sort(comparador);
		
		palavras.forEach(s -> System.out.println(s));
		
		System.out.println("");
	}
	
	// Method Reference
	public static void formaComMethodReference2() {
		Function<String, Integer> funcao = s -> s.length();
		
		Comparator<String> comparador = Comparator.comparing(funcao);
		palavras.sort(comparador);
		
		palavras.forEach(s -> System.out.println(s));
		
		System.out.println("");
	}
		
	// Method Reference
	public static void formaComMethodReference3() {
		palavras.sort(Comparator.comparing(s -> s.length()));
		
		palavras.forEach(s -> System.out.println(s));
		
		System.out.println("");
	}
	
	// Method Reference
	public static void formaComMethodReference4() {
		palavras.sort(Comparator.comparing(String::length));
		
		palavras.forEach(s -> System.out.println(s));
		
		System.out.println("");
	}
	
	// Method Reference
	public static void formaComMethodReference5() {
		palavras.sort(Comparator.comparing(String::length));
		
		Consumer<String> impressor = System.out::println;
		
		palavras.forEach(impressor);
		
		System.out.println("");
	}
	
	// Streams
	public static void exemploCursos1() {
		cursos.sort(Comparator.comparing(Curso::getAlunos));
		cursos.forEach(c -> System.out.println(c.getNome()));
		
		System.out.println("");
	}
	
	// Streams
	public static void exemploCursos2() {
		cursos.sort(Comparator.comparing(Curso::getAlunos));
		
		cursos.stream().filter(c -> c.getAlunos() >= 100)
		.forEach(c -> System.out.println(c.getNome()));
		
		System.out.println("");
	}
	
	// Streams
	public static void exemploCursos3() {
		cursos.sort(Comparator.comparing(Curso::getAlunos));
		
		cursos.stream().filter(c -> c.getAlunos() >= 100)
		.map(c -> c.getAlunos())
		.forEach(System.out::println);
		
		System.out.println("");
	}
	
	// Streams
	public static void exemploCursos4() {
		cursos.sort(Comparator.comparing(Curso::getAlunos));
		
		IntStream intStream = cursos.stream().filter(c -> c.getAlunos() >= 100)
		.mapToInt(c -> c.getAlunos());
		
		System.out.println(intStream.sum());
		
		System.out.println("");
	}

	// Streams
	public static void exemploCursos5() {
		// Obs: Comparator.comparingInt vs Comparator.comparing
		// pode dar muita diferença de performance em quantidades de dados
		// muito grandes.
		cursos.sort(Comparator.comparingInt(Curso::getAlunos));
		
		int soma = cursos.stream().filter(c -> c.getAlunos() >= 100)
		.mapToInt(c -> c.getAlunos())
		.sum();
		
		System.out.println(soma);
		
		System.out.println("");
	}
	
	// Streams
	public static void exemploCursos6() {
		cursos.sort(Comparator.comparingInt(Curso::getAlunos));
		
		Optional<Curso> optionalCurso = cursos.stream().filter(c -> c.getAlunos() >= 100)
		.findAny();

		optionalCurso.get(); // Lança uma NoSuchElement se não existir.
		optionalCurso.orElse(null); // Apenas retorna nulo se não existir.
		
		Curso curso = optionalCurso.orElse(null);
		System.out.println(curso.getNome());
		
		optionalCurso.ifPresent(c -> System.out.println(c.getNome()));
		
		System.out.println("");
	}
	
	// Streams
	public static void exemploCursos7() {
		cursos.sort(Comparator.comparingInt(Curso::getAlunos));
		
		cursos.stream().filter(c -> c.getAlunos() >= 100)
		.findAny()
		.ifPresent(c -> System.out.println(c.getNome()));
		
		System.out.println("");
	}
	
	// Streams
	public static void exemploCursos8() {
		cursos.sort(Comparator.comparingInt(Curso::getAlunos));
		
		OptionalDouble media = cursos.stream().filter(c -> c.getAlunos() >= 100)
		.mapToInt(Curso::getAlunos)
		.average();
		
		media.ifPresent(System.out::println);
		
		System.out.println("");
	}
	
	// Streams
	public static void exemploCursos9() {
		cursos.sort(Comparator.comparingInt(Curso::getAlunos));
		
		cursos = cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.collect(Collectors.toList());
		
		cursos.stream().forEach(c -> System.out.print(c.getNome()));
		
		System.out.println("");
	}

	// Streams
	public static void exemploCursos10() {
		cursos.sort(Comparator.comparingInt(Curso::getAlunos));
		
		Map<String, Integer> map = cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.collect(Collectors.toMap(
					c -> c.getNome(),
					c -> c.getAlunos()));
		
		System.out.println(map);
		
		System.out.println("");
	}
	
	// Streams
	public static void exemploCursos11() {
		cursos.sort(Comparator.comparingInt(Curso::getAlunos));
		
		cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.collect(Collectors.toMap(
					c -> c.getNome(),
					c -> c.getAlunos()))
			.forEach((nome, alunos) -> System.out.println(nome + " tem " + alunos + " alunos." ));
		
		System.out.println("");
	}
	
	// Streams
	public static void exemploCursos12() {
		cursos.sort(Comparator.comparingInt(Curso::getAlunos));
		
		// Processamento paralelo em Streams.
		cursos.parallelStream()
			.filter(c -> c.getAlunos() >= 100)
			.collect(Collectors.toMap(
					c -> c.getNome(),
					c -> c.getAlunos()))
			.forEach((nome, alunos) -> System.out.println(nome + " tem " + alunos + " alunos." ));
		
		System.out.println("");
	}
	
	// LocalDate, LocalDateTime
	public static void exemploDate1() {
		LocalDate hoje = LocalDate.now();
		
		System.out.println(hoje);
		
		System.out.println("");
	}
	
	// LocalDate
	public static void exemploDate2() {
		LocalDate hoje = LocalDate.now();
		
		System.out.println(hoje);
		
		LocalDate dataFutura = LocalDate.of(2025, Month.NOVEMBER, 2);
		
		int anos = dataFutura.getYear() - hoje.getYear();
		
		System.out.println(anos);
		
		Period periodo = Period.between(hoje, dataFutura);
		System.out.println(periodo);
		System.out.println(periodo.getDays());
		System.out.println(periodo.getMonths());
		System.out.println(periodo.getYears());
		
		// Ver Period.TemporalUnit, para criar unidades temporais personalizadas.
		
		System.out.println("");
	}
	
	// LocalDate
	public static void exemploDate3() {
		LocalDate hoje = LocalDate.now();
		
		LocalDate dataFutura = LocalDate.of(2025, Month.NOVEMBER, 2);
		
		Period periodo = Period.between(hoje, dataFutura);
		
		dataFutura.plusYears(9);
		
		// Não altera a dataFutura, é necessário atribuir
		System.out.println(dataFutura);
		
		LocalDate novaData = dataFutura.plusYears(9);
		System.out.println(novaData);
		
		System.out.println("");
	}
	
	// LocalDate
	public static void exemploDate4() {
		LocalDate hoje = LocalDate.now();
		
		LocalDate dataFutura = LocalDate.of(2025, Month.NOVEMBER, 2);
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String valorFormatado = dataFutura.format(formatador);
		
		// Pode-se utilizar Locale também.
		System.out.println(valorFormatado);
		
		System.out.println("");
	}
	
	// LocalDateTime
	public static void exemploDate5() {
		LocalDateTime hoje = LocalDateTime.now();
		
		LocalDate dataFutura = LocalDate.of(2025, Month.NOVEMBER, 2);
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatadorHoras = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
		
		String valorFormatadoHoje = hoje.format(formatadorHoras);
		String valorFormatadoFutura = dataFutura.format(formatador);
		
		System.out.println(valorFormatadoHoje);
		System.out.println(valorFormatadoFutura);
		
		System.out.println("");
	}
	
	// LocalDateTime
	public static void exemploDate6() {
		LocalTime intervalo = LocalTime.of(15, 30);
		
		System.out.println(intervalo);
		System.out.println(YearMonth.now());
		
		// Ver
		// YearMonth
		// LocalTime
		
		System.out.println("");
	}
}


// Modo obsoleto, apenas para exemplo.
class ImprimeNaLinha implements Consumer<String> {

	@Override
	public void accept(String s) {
		System.out.println(s);
	}
	
}

// Modo obsoleto, apenas para exemplo.
class ComparadorPorTamanho implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		if(s1.length() < s2.length())
			return -1;
		if(s1.length() > s2.length())
			return 1;
		return 0;
	}
	
}