$listener = [System.Net.Sockets.TcpListener]1050
$listener.Start()
$client = $listener.AcceptTcpClient()
$stream = $client.GetStream()
$reader = New-Object System.IO.StreamReader($stream)

while ($reader.Peek() -ge 0) {
    Write-Output $reader.ReadLine()
}

$reader.Close()
$stream.Close()
$client.Close()
$listener.Stop()