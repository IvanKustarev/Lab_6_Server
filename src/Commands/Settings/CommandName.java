package Commands.Settings;

public enum CommandName {
    ADD{
        @Override
        public String toString() {
            return "help";
        }
    },
    CLEAR{
        @Override
        public String toString() {
            return "clear";
        }
    },
    EXECUTE_SCRIPT{
        @Override
        public String toString() {
            return "execute_script";
        }
    },
    EXIT{
        @Override
        public String toString() {
            return "exit";
        }
    },
    FILTER_CONTAINS_NAME{
        @Override
        public String toString() {
            return "filter_contains_name";
        }
    },
    FILTER_LESS_THAN_GOVERNOR{
        @Override
        public String toString() {
            return "filter_less_than_governor";
        }
    },
    HEAD{
        @Override
        public String toString() {
            return "head";
        }
    },
    HELP{
        @Override
        public String toString() {
            return "help";
        }
    },
    INFO{
        @Override
        public String toString() {
            return "info";
        }
    },
    PRINT_FIELD_DESCENDING_GOVERNMENT{
        @Override
        public String toString() {
            return "show";
        }
    },
    REMOVE_BY_ID{
        @Override
        public String toString() {
            return "remove_by_id";
        }
    },
    REMOVE_FIRST{
        @Override
        public String toString() {
            return "remove_first";
        }
    },
    REMOVE_HEAD{
        @Override
        public String toString() {
            return "remove_head";
        }
    },
    SHOW{
        @Override
        public String toString() {
            return "show";
        }
    },
    UPDATE{
        @Override
        public String toString() {
            return "update";
        }
    },
    LOGIN{
        @Override
        public String toString() {
            return "login";
        }
    },
    REGISTER{
        @Override
        public String toString() {
            return "register";
        }
    }
}
