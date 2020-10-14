$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoCliente = button.data('id');
	var nome = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.method = 'DELETE';
	
	form.attr('action', action + 'DELETE/' + codigoCliente);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o cliente <strong>' + nome + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
});