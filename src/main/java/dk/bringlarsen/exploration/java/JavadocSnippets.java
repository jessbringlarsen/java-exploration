package dk.bringlarsen.exploration.java;

/**
 * Exploration of JEP-413: Code Snippets in Java API Documentation.
 * Se <a href="https://https://blogs.oracle.com/javamagazine/post/java-javadoc-snippet">Javadoc documents are great—and better than ever with @snippets</a> for more.
 * <p>
 * Generate the javadoc with {@snippet lang="TEXT" :
 *   mvn javadoc:javadoc
 * }
 * </p>
 */
@JDK(version = 18)
public class JavadocSnippets {


    /**
     * A demo of the @snippet tag to include non-escaping special characters.

     * {@snippet id='specialChars' :
     * int age = 42;
     * <html>Here is HTML tags works</html>
     * & also works;
     * @ also works;
     * }
     */
    public void inline() {
        System.out.println("Code @snippets special characters demo.");
    }

    /**
     *  Demo for code @snippets Linking. Notice the anchor tags generated in the javadoc.
     * {@snippet id="linking" lang="java" :
     * // @link substring="System.out" target="System#out":
     * System.out.println("Link to System.out");
     * // @link substring="linkTarget()" target="#linkTarget" type="link" :
     * app.linkTarget();
     * }
     */
    public void linking() {
        // NOP
    }

    public void linkTarget() {
        // NOP
    }

    /**
     * Demo for code @snippets Highlighting.
     * <p>
     *     {@snippet id="highlighting" lang="java" :
     *         System.out.println("Highlighting System.out"); // @highlight regex='(System.out)(?="\);)' type="bold"
     *         System.out.println("Highlighting System.out"); // @highlight substring="Highlighting" type="italic"
     *     }
     *  }
     * </p>
     */
    public void highlighting() {
        // NOP
    }

    /**
     * By instructing the javadoc plugin to fetch snippets from the <i>src/test/java</i> directory we can link to specific regions of the code to e.g.
     * give usage examples:
     * {@snippet class="dk.bringlarsen.exploration.java.http.HttpClientExplorationTest" region="HttpClientExample"}
     */
    public void externalContent() {
    }
}
