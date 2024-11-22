package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameLoadTest {

    @Test
    public void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("no data");
    }

    @Test
    public void checkParseEmptyArray() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(new String[] {}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Names array is empty");
    }

    @Test
    public void checkParseNameDoesNotContainEqualsSign() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"name=Sergei", "name:Petr", "name=Andrei"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("name:Petr",
                        "not contains the symbol '='");
    }

    @Test
    public void checkParseNameDoesNotContainsKey() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"name=Sergei", "name=Petr", "=Andrei"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=Andrei",
                        "does not contain a key");
    }

    @Test
    public void checkParseNameDoesNotContainsValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"name=Sergei", "name=", "name=Andrei"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("name=",
                        "does not contains a value");
    }

    @Test
    public void checkParseIsSuccessWithDuplicateKeys() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"name=Sergei", "name=Petr", "name=Andrei"};
        nameLoad.parse(names);
        assertThat(nameLoad.getMap()).containsEntry("name", "Sergei+Petr+Andrei");
    }

    @Test
    public void checkParseIsSuccessWithDifferentKeys() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"name1=Sergei", "name2=Petr", "name3=Andrei"};
        nameLoad.parse(names);
        assertThat(nameLoad.getMap()).containsEntry("name1", "Sergei")
                .containsEntry("name2", "Petr")
                .containsEntry("name3", "Andrei");
    }
}
