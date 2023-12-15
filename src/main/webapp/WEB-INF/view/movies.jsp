<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value='${sessionScope["user"]}' var="user"/>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:header title="Movies"/>
<body>
<div class="wrapper">
    <main class="content">
        <div class="content-top">
            <c:if test="${user.getRole().toString() == 'ADMIN'}">
                <button class="add" onclick="openAddMovieModal()">
                    <span>+</span>
                    Add new movie
                </button>
            </c:if>
        </div>
        <div class="movies">
            <c:forEach var="movie" items="${movies}">
                <div class="movie">
                    <a href="${pageContext.servletContext.contextPath}/movies/${movie.getId()}">
                        <img src="${movie.getImgUrl()}" alt="${movie.getTitle()}" class="poster">
                        <div class="rating">${movie.getRating()}</div>
                        <div class="name">${movie.getTitle()}</div>
                    </a>
                    <c:if test="${user.getRole().toString() == 'ADMIN'}">
                        <button class="add edit"
                                onclick="openEditMovieModal(`${movie.getId()}`, `${movie.getTitle()}`, `${movie.getDescription()}`, `${movie.getImgUrl()}`)">
                            Edit movie
                        </button>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <div class="modal-wrapper" id="addMovieModal">
            <div class="modal-form">
                <h2 class="modal-header">Add new movie</h2>
                <form action="${pageContext.servletContext.contextPath}/movies/add" method="post">
                    <label for="addMovieTitle">Movie Title:</label>
                    <input type="text" name="title" id="addMovieTitle" required>

                    <label for="addMovieDescription">Movie Description:</label>
                    <textarea name="description" id="addMovieDescription" required></textarea>

                    <label for="addMovieImgUrl">Movie Image URL:</label>
                    <input type="text" name="imgUrl" id="addMovieImgUrl" required>

                    <button class="modal-submit" type="submit">
                        Add
                    </button>
                </form>
            </div>
        </div>
        <div class="modal-wrapper" id="editMovieModal">
            <div class="modal-form">
                <h2 class="modal-header">Edit movie</h2>
                <form action="${pageContext.servletContext.contextPath}/movies/edit" method="post">
                    <input type="hidden" name="movieId" id="editMovieId" value="">
                    <label for="editMovieTitle">Movie Title:</label>
                    <input type="text" name="title" id="editMovieTitle" required>

                    <label for="editMovieDescription">Movie Description:</label>
                    <textarea name="description" id="editMovieDescription" required></textarea>

                    <label for="editMovieImgUrl">Movie Image URL:</label>
                    <input type="text" name="imgUrl" id="editMovieImgUrl" required>

                    <button class="modal-submit" type="submit">
                        Confirm
                    </button>
                </form>
            </div>
        </div>
    </main>
</div>
<script>
    openAddMovieModal = () => {
        const modal = document.getElementById("addMovieModal");
        modal.style.display = "flex";
    }

    openEditMovieModal = (movieId, movieTitle, movieDescription, imgUrl) => {
        const modal = document.getElementById("editMovieModal");
        modal.style.display = "flex";
        document.getElementById("editMovieId").value = movieId;
        document.getElementById("editMovieTitle").value = movieTitle;
        document.getElementById("editMovieDescription").value = movieDescription;
        document.getElementById("editMovieImgUrl").value = imgUrl;
    }

    window.onclick = (event) => {
        const addModal = document.getElementById("addMovieModal");
        const editModal = document.getElementById("editMovieModal");
        if (event.target === addModal) {
            addModal.style.display = "none";
        }
        if (event.target === editModal) {
            editModal.style.display = "none";
        }
    }
</script>
</body>