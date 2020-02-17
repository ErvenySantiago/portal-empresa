$('#confirmacaoExclusaoModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var dataId = button.data('id') // Extract info from data-* attributes
  
  var modal = $(this)
  var form = modal.find('form');
  var action = form.attr('action');
  if(!action.endsWith('/')){
	  action += '/';
  }
  form.attr('action',action + dataId)
})