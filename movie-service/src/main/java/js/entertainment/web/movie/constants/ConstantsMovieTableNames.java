package js.entertainment.web.movie.constants;

public class ConstantsMovieTableNames {
    public static class CategoryTable {
        public static final String NAME = "categories_table";

        public static class Id {
            public static final String NAME = "category_id";
        }

        public static class Category {
            public static final String NAME = "category";
            public static final Integer LENGTH = 20;
        }
    }

    public static final class ClassificationTable {
        public static final String NAME = "classifications_table";

        public static final class Id {
            public static final String NAME = "classification_id";
        }

        public static final class Classification {
            public static final String NAME = "classification";
            public static final Integer LENGTH = 20;
        }
    }

    public static class MovieTable {
        public static final String NAME = "movie_table";

        public static class Id {
            public static final String NAME = "movie_id";
        }

        public static class ImageId {
            public static final String ID = "image_id";
        }

        public static class Name {
            public static final String NAME = "name";
            public static final Integer LENGTH = 50;
        }

        public static class RelaseDate {
            public static final String NAME = "release_date";
        }
    }
}
