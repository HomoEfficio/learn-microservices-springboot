
const updateLeaderBoard = _ => {
  $.ajax({
    url: 'http://localhost:8081/leaders'
  }).then(data => {
    $('#leaderboard-body').empty();
    data.forEach(row => {
      $('#leaderboard-body').append('<tr><td>' + row.userId + '</td>' + '<td>' + row.totalScore + '</td>');
    });
  });
};

const updateStats = userId => {
  $.ajax({
    url: 'http://localhost:8081/stats?userId=' + userId,
    success: data => {
      $('#stats-div').show();
      $('#stats-user-id').empty().append(userId);
      $('#stats-score').empty().append(data.score);
      $('#stats-badges').empty().append(data.badges.join());
    },
    error: data => {
      $('#stats-div').show();
      $('#stats-user-id').empty().append(userId);
      $('#stats-score').empty().append(0);
      $('#stats-badges').empty();
    }
  });
};

$(document).ready(_ => {
  updateLeaderBoard();
  $('#refresh-leaderboard').click(event => {
    updateLeaderBoard();
  });
});
