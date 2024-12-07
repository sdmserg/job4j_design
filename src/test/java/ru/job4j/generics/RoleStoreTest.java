package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Admin");
    }

    @Test
    public void whenAddAndFindRoleisNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        Role result = store.findById("100");
        assertThat(result).isNull();
    }

    @Test
    public void whenAddDuplicateThenUsernmaeIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.add(new Role("1", "Viewer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceThenRoleIsViewer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.replace("1", new Role("1", "Viewer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Viewer");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.replace("10", new Role("10", "Viewer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Admin");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        boolean result = store.replace("1", new Role("1", "Viewer"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        boolean result = store.replace("10", new Role("10", "Viewer"));
        assertThat(result).isFalse();
    }
}
