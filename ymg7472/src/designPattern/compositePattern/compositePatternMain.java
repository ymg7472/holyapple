package designPattern.compositePattern;

/**
 * <pre>
 * kr.co.swh.lecture.engineering.scene3.composite
 * Main.java
 *
 * 설명 :composite 패턴 메인
 * </pre>
 * 
 * @since : 2017. 10. 3.
 * @author : tobby48
 * @version : v1.0
 */
public class compositePatternMain {
	public static void main(String[] args) {
		try{
			System.out.println("Making root entries...");
			Directory rootdir = new Directory("root");
			Directory bindir = new Directory("bin");
			Directory tmpdir = new Directory("tmp");
			Directory usrdir = new Directory("usr");

			rootdir.add(bindir);
			rootdir.add(tmpdir);
			rootdir.add(usrdir);
			bindir.add(new File("vi", 10000));
			bindir.add(new File("latex", 20000));

			rootdir.printList();

			System.out.println("");
			System.out.println("Making user entries...");

			Directory koya = new Directory("Koya");
			Directory raison = new Directory("Raison");
			Directory yuki = new Directory("Yuki");
			usrdir.add(koya);
			usrdir.add(raison);
			usrdir.add(yuki);
			koya.add(new File("diary.html", 100));
			koya.add(new File("Composite.java", 200));
			raison.add(new File("memo.tex", 300));
			yuki.add(new File("game.doc", 400));
			yuki.add(new File("junk.mail", 500));

			rootdir.printList();
		} catch (FileTreatmentException e) {
			e.printStackTrace();
		}
	}
}