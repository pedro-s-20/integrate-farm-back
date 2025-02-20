<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Novo agendamento</title>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="center" valign="top" bgcolor="#80aaff"
            style="background-color: #80aaff;"><br><strong><em><span style="color: #ffffff">Integrate Farm</span></em></strong><br>
            <table width="600" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td align="center" valign="top" bgcolor="#ffffff"
                        style="background-color: #ffffff; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 15px 10px 15px;">
                    <h2> Olá ${agendamento.getClienteEntity().getUsuarioEntity().getNome()}, </h2>
                    <p>
                        Uma nova consulta foi agendada para você. Segue as informações: <br>
                        <strong>ID do agendamento: ${agendamento.getIdAgendamento()}</strong><br>
                        <strong>Data e horário: ${agendamento.getDataHorario()} <strong>
                        Prestador de serviço que lhe atenderá: ${agendamento.getPrestadorServicoEntity().getUsuarioEntity().getNome()} <br>
                        Status: ${agendamento.getStatus()} <br>
                        <em><strong>OBS.:</strong> Caso o agendamento esteja PENDENTE,
                        algum de nossos colaboradores irá avaliar a disponibilidade do prestador de serviço e confirmará ou não o atendimento.<br>
                        Fique tranquilo, avisaremos qualquer atualização! ;)</em>
                        Qualquer dúvida é só contatar o suporte pelo e-mail ${email}<br>
                        Att,<br>
                        Integrate Farm Corp.<br>
                    </p>
                    </td>
                </tr>
            </table> <br> <br></td>
    </tr>
</table>

</body>
</html>