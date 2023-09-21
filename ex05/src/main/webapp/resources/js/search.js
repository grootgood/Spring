$(document).ready(function() {

		// required. 필수 요소 체크 코드
		let searchForm = $('#searchForm'); // 자손
		$('#searchForm button').on('click', function(e) { // 후손. 검색 버튼
			let type = searchForm.find('option:selected'); // : 은 상태 선택자
			if (!type.val()) {
				alert('검색 종류를 선택하세요.');
				type.focus();
				return false; //submit 하지 말라는 뜻
			}

			let keyword = searchForm.find('input[name="keyword"]');
			if (!keyword.val()) {
				alert('키워드를 입력하세요.');
				keyword.focus();
				return false;
			}

			searchForm.find('input[name="pageNum"]').val('1');
			e.preventDefault();

			searchForm.submit();

		});
});