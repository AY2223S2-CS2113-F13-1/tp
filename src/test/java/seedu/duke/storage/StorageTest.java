package seedu.duke.storage;

import org.junit.jupiter.api.Test;

import java.io.File;

import static seedu.duke.storage.Storage.deleteFile;

public class StorageTest {
    /**
     * Test for deleting a file.
     */
    @Test
    public void deleteTestFileA() {
        File file = new File("/temp/lock11-1");
        try {
            file.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        assert (file.exists());
        try {
            deleteFile(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert false;
        }
        assert (!(file.exists()));
    }

    /**
     * Test for deleting a directory with files inside.
     */
    @Test
    public void deleteTestFileB() {
        File file1 = new File("/temp/lock11-1");
        File file2 = new File("/temp/lock11-2");
        File dir = new File("/temp");
        try {
            file1.createNewFile();
            file2.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        assert (file1.exists());
        assert (file2.exists());
        try {
            deleteFile(dir);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert false;
        }
        assert (!(file1.exists()));
        assert (!(file2.exists()));
    }

    /**
     * Test for deleting a directory with files inside recursively.
     */
    @Test
    public void deleteTestFileC() {
        File file1 = new File("/temp/lock11-1");
        File file2 = new File("/temp/lock11-2");
        File file3 = new File("/temp/temp/lock11-3");
        File file4 = new File("/temp/temp/lock11-4");
        File dir = new File("/temp");
        File dir2 = new File("/temp/temp");
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!dir2.exists()) {
            dir2.mkdir();
        }
        try {
            file1.createNewFile();
            file2.createNewFile();
            file3.createNewFile();
            file4.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        assert (file1.exists());
        assert (file2.exists());
        assert (file3.exists());
        assert (file4.exists());
        try {
            deleteFile(dir);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert false;
        }
        assert (!(file1.exists()));
        assert (!(file2.exists()));
        assert (!(file3.exists()));
        assert (!(file4.exists()));
    }
}
